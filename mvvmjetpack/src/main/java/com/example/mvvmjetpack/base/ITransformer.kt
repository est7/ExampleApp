package com.example.mvvmjetpack.base

interface ITransformer<In, Out> {
    fun transform(input: In): Out
}