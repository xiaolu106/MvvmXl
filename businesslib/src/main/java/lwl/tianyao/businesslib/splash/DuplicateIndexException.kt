package lwl.tianyao.businesslib.splash

class DuplicateIndexException : Exception {

    constructor(message: ISplashComposableTask) : super("${message.index} is duplicate. Class name is ${message.content.toString()}")

}