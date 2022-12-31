package com.example.contacts.models

import kotlin.random.Random

typealias AccountListener = (accounts: List<Account>) -> Unit

class AccountService {
    var accounts = mutableListOf<Account>()

    private val listeners = mutableSetOf<AccountListener>()

    fun addAccount(name_ :String, phone_ : String ){
        val newIdAccount = Random.nextInt(1000)
        val newAccount = Account(newIdAccount.toString(),name_,phone_)
        accounts.add(newAccount)
        notifyChanges()
    }

    fun deleteAccount(account: Account){
        val indexToDelete = accounts.indexOfFirst { it.id ==account.id}
        if (indexToDelete != -1){
            accounts.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addListener(listener: AccountListener){
        listeners.add(listener)
        listener.invoke(accounts)
    }

    fun removeListener(listener: AccountListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(accounts) }
    }
}