package com.p209.dinero.core.common.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dineroDispatcher: DineroDispatchers)

enum class DineroDispatchers { IO }