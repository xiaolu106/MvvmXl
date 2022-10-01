//package com.alibaba.android.arouter.compiler
//
//import com.alibaba.android.arouter.facade.annotation.Route
//import com.squareup.kotlinpoet.FileSpec
//import com.squareup.kotlinpoet.KModifier
//import com.squareup.kotlinpoet.PropertySpec
//import javax.annotation.processing.ProcessingEnvironment
//import javax.annotation.processing.RoundEnvironment
//import javax.lang.model.element.Element
//import javax.tools.Diagnostic
//
//object RouterConfigUtils {
//
//    fun generate(
//             roundEnvironment: RoundEnvironment,
//              processingEnv: ProcessingEnvironment
//    ){
//
//        val messager = processingEnv.messager
//        messager.printMessage(Diagnostic.Kind.NOTE, "========111111====>")
//
//        val elementsSet = roundEnvironment.getElementsAnnotatedWith(Route::class.java)
//        val propertySpecs: MutableList<PropertySpec> = mutableListOf()
//
//        for (element: Element in elementsSet) {
//            element.getAnnotation(Route::class.java).apply {
//                propertySpecs.add(
//                    PropertySpec.builder(
//                    this.path.substring(this.path.lastIndexOf("/") + 1).uppercase(),
//                    String::class,
//                    KModifier.PUBLIC,
//                ).initializer("%S", this.path).build())
//            }
//        }
//
//        val objectBuilder = com.squareup.kotlinpoet.TypeSpec.objectBuilder("ARouterConfig")
//            .addProperties(propertySpecs)
//            .build()
//
//        //3 生成kotlin文件
//        val file = FileSpec.builder("", "ARouterConfig")
//            .addType(objectBuilder)
//            .build()
//
//        //导出文件
//        file.writeTo(processingEnv.filer)
//        file.writeTo(System.out)
//    }
//}