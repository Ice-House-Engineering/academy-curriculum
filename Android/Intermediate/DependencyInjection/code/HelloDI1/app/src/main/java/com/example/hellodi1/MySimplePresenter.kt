package com.example.hellodi1


class MySimplePresenter(val repo: HelloRepository) {
    fun sayHello() = "${repo.giveHello()} from presenter"
}