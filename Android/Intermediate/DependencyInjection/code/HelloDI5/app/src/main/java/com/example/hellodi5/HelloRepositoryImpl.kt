package com.example.hellodi5


class HelloRepositoryImpl : HelloRepository {

    var hello = "Hello"

    override fun giveHello() = "$hello Koin"
}