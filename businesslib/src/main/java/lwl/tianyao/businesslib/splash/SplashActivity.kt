package lwl.tianyao.businesslib.splash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/splash/SplashActivity")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.post {
            setContent {
                val viewModel: SplashViewModel = viewModel()
                viewModel.executeCurrentTask()

            }
        }
    }


    override fun onStop() {
        super.onStop()
        finish()
    }
}