package com.dragonfly.banklisttest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dragonfly.banklisttest.databinding.BankItemListBinding
import com.dragonfly.banklisttest.model.Bank

class BankListAdapter(private val bankList: List<Bank>) : RecyclerView.Adapter<BankListAdapter.BankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        return BankViewHolder(
            BankItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val plant = bankList[position]
        holder.bind(plant)
    }

    class BankViewHolder(private val binding: BankItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bank) {
            binding.apply {
                bank = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return bankList.size
    }
}
