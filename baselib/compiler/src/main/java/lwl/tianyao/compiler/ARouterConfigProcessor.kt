package lwl.tianyao.compiler

import com.alibaba.android.arouter.facade.annotation.Route
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


@SupportedAnnotationTypes("com.alibaba.android.arouter.facade.annotation.Route")
class ARouterConfigProcessor : AbstractProcessor() {

    override fun process(
        set: MutableSet<out TypeElement>,
        roundEnvironment: RoundEnvironment
    ): Boolean {

        val messager = processingEnv.messager
        messager.printMessage(Diagnostic.Kind.NOTE, "========111111====>")

        if (!set.isEmpty()){
            val elementsSet = roundEnvironment.getElementsAnnotatedWith(Route::class.java)
            val propertySpecs: MutableList<PropertySpec> = mutableListOf()

            for (element: Element in elementsSet) {
                element.getAnnotation(Route::class.java).apply {
                    propertySpecs.add(PropertySpec.builder(
                        this.path.substring(this.path.lastIndexOf("/") + 1).uppercase(),
                        String::class,
                        KModifier.PUBLIC,
                    ).initializer("%S", this.path).build())
                }
            }

            val objectBuilder = TypeSpec.objectBuilder("ARouterConfig")
                .addProperties(propertySpecs)
                .build()

            //3 生成kotlin文件
            val file = FileSpec.builder("", "ARouterConfig")
                .addType(objectBuilder)
                .build()

            //导出文件
            file.writeTo(processingEnv.filer!!)
            file.writeTo(System.out)
        }

        return true
    }

//    override fun getSupportedAnnotationTypes(): MutableSet<String> {
//        return super.getSupportedAnnotationTypes()
//    }
}