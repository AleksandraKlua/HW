package com.example.lesson1.networking.model

data class UserNet(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address
)

data class Address(
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)