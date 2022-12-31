package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemAccountBinding
import com.example.contacts.models.Account


class AccountsAdapter(
    private val listener:Listener
): RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>(),
    View.OnClickListener{

    var accounts: List<Account> = emptyList()
        set(newValue){
            field = newValue
            notifyItemInserted(accounts.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAccountBinding.inflate(inflater,parent,false)

        binding.cardViewAccount.setOnClickListener(this)
        binding.phoneButton.setOnClickListener(this)

        return AccountsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val account = accounts[position]
        with(holder.binding){
            root.tag = account
            phoneButton.tag = account
            cardViewAccount.tag = account

            nameTextView.text = account.name
            phoneTextView.text = account.phone
        }
    }

    override fun getItemCount(): Int = accounts.size

    class AccountsViewHolder(
        val binding: ItemAccountBinding
    ): RecyclerView.ViewHolder(binding.root)

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