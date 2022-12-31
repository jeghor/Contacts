package com.example.contacts

import android.app.Application
import com.example.contacts.models.AccountService
import com.example.contacts.models.EntryFieldService

class App: Application() {
    companion object{
        val accountService = AccountService()
        val entryFieldService = EntryFieldService()
    }
}