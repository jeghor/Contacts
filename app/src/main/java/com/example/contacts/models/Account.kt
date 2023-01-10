package com.example.contacts.models


data class Account (
    val id: String,
    var name: String,
    var phone: String,
    var email: String,
    var account_type: Int = 0, //0 - phone number, 1 - email
        )