package com.example.hellodi3


class HelloRepositoryImpl(val string: String) : HelloRepository {
    override fun giveHello() = "Hello Koin with $string"
}