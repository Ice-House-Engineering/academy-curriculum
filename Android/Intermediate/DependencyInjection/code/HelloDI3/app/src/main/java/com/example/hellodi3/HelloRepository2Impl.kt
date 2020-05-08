package com.example.hellodi3


class HelloRepository2Impl(val string: String) : HelloRepository {
    override fun giveHello() = "Hello Koin 2 with $string"
}