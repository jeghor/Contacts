package com.example.contacts.models

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class EntryFieldService{
    fun checkField(context: FragmentActivity,name: EditText, phone: EditText):Boolean{
        var field = false

        if ("${name.text}"!= ""&& "${phone.text}"!= ""){
            field = true
        } else if ("${name.text}"== ""&& "${phone.text}"== ""){
            Toast.makeText(context,"Please, enter name and phone number", Toast.LENGTH_SHORT).show()
        } else if ("${phone.text}"== ""){
            Toast.makeText(context,"Please, enter phone number", Toast.LENGTH_SHORT).show()
        } else if ("${name.text}"== ""){
            Toast.makeText(context,"Please, enter name", Toast.LENGTH_SHORT).show()
        }

        return field
    }
}