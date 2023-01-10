package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.models.Account

const val ACC_TYPE_PHONE: Int = 0
const val ACC_TYPE_EMAIL: Int = 1

class AccountsAdapter(
    private val listener:Listener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    View.OnClickListener{

    var accounts: List<Account> = emptyList()
        set(newValue){
            field = newValue
            notifyItemInserted(accounts.size)
        }

    class PhoneViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(accModel: Account){
            val name = itemView.findViewById<TextView>(R.id.name_textView)
            val phone = itemView.findViewById<TextView>(R.id.phone_textView)
            name.text = accModel.name
            phone.text = accModel.phone
        }
    }

    class EmailViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(accModel: Account){
            val name = itemView.findViewById<TextView>(R.id.name_textView)
            val email = itemView.findViewById<TextView>(R.id.email_textView)
            name.text = accModel.name
            email.text = accModel.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ACC_TYPE_PHONE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account,parent,false)
            val cardView = view.findViewById<CardView>(R.id.cardViewAccount)
            cardView.setOnClickListener(this)
            val phoneButton = view.findViewById<ImageButton>(R.id.phone_button)
            phoneButton.setOnClickListener(this)
            PhoneViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email,parent,false)
            val cardView = view.findViewById<CardView>(R.id.cardViewAccount)
            cardView.setOnClickListener(this)
            val emailButton = view.findViewById<ImageButton>(R.id.email_letter_button)
            emailButton.setOnClickListener(this)
            EmailViewHolder(view)
        }
    }

    override fun getItemCount(): Int = accounts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val account = accounts[position]
        if (getItemViewType(position) == ACC_TYPE_PHONE){
            with((holder as PhoneViewHolder)){
                bind(accounts[position])
                val cardView = itemView.findViewById<CardView>(R.id.cardViewAccount)
                cardView.tag = account
                val phoneButton = itemView.findViewById<ImageButton>(R.id.phone_button)
                phoneButton.tag = account
            }
        } else{
            with(holder as EmailViewHolder){
                bind(accounts[position])
                val cardView = itemView.findViewById<CardView>(R.id.cardViewAccount)
                cardView.tag = account
                val emailButton = itemView.findViewById<ImageButton>(R.id.email_letter_button)
                emailButton.tag = account
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (accounts[position].account_type == 0){
            ACC_TYPE_PHONE
        } else{
            ACC_TYPE_EMAIL
        }
    }

    interface Listener{
        fun onContact(account: Account)
        fun onCallContact(account: Account)
    }

    override fun onClick(v: View) {
        val contact = v.tag as Account
        when(v.id){
            R.id.cardViewAccount -> listener.onContact(contact)
            else -> listener.onCallContact(contact)
        }
    }
}