package lwl.tianyao.businesslib.splash

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.auto.service.AutoService

@AutoService(ISplashComposableTask::class)
class AppSignatureVerificationComposable : ISplashComposableTask {

    override val content: @Composable (viewModel: SplashViewModel) -> Unit={
          AppSignatureVerificationComposableSection(it)
    }

    override val index: Int
        get() = 0

}

@Composable
fun AppSignatureVerificationComposableSection(viewModel: SplashViewModel){
    val signCheck = SignCheck(
        LocalContext.current,
        "C4:E7:DB:4C:7E:5F:9F:05:D2:FA:8E:4D:18:43:DF:67:EF:C6:7E:72"
    )
    if (signCheck.check()) {
        viewModel.findNextTask()
    } else {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    viewModel.exitApp()
                },
                title = {
                    Text(text = "警告")
                },
                text = {
                    Text(text = "您当前使用的应用非官方版本，请前往官方渠道下载正版。")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            viewModel.exitApp()
                        }) {
                        Text("退出去应用商店下载")
                    }
                },
                modifier = Modifier.fillMaxWidth(fraction = 0.90f)
            )
        }
    }
}