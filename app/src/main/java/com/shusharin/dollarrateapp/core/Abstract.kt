package com.shusharin.dollarrateapp.core

abstract class Abstract {

    interface Object<T, M> {
        fun map(M: Mapper): T
    }

    interface Mapper {
        class Empty
    }
}