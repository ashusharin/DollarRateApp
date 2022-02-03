package com.shusharin.dollarrateapp.core

abstract class Abstract {

    interface Object<T, M> {
        fun map(mapper:M): T
    }

    interface Mapper {
        class Empty
    }
}