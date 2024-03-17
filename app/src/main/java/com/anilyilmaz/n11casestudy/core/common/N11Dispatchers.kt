package com.anilyilmaz.n11casestudy.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val n11Dispatchers: N11Dispatchers)

enum class N11Dispatchers {
    IO
}