package com.example.hellodi2


class MySimplePresenter(val repo: HelloRepository) {
    fun sayHello() = "${repo.giveHello()} from presenter"
}