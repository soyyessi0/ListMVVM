package com.dragonfly.banklisttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonfly.banklisttest.R
import com.dragonfly.banklisttest.utils.InjectorUtils
import com.dragonfly.banklisttest.viewmodel.BankListViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bankListViewModel: BankListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        bankListViewModel =
            ViewModelProvider(
                this,
                InjectorUtils(this, "https://api.jsonbin.io/b/5ea2fa3e98b3d5375233ca89")
            )
                .get(BankListViewModel::class.java)

        bankListViewModel.startService()
        bankListViewModel.banks.observe(this, Observer { bankList ->
            bankListRecycler.layoutManager = LinearLayoutManager(this)
            bankListRecycler.adapter = BankListAdapter(bankList)
            progress_circular.visibility = View.GONE
        })

        bankListViewModel.error.observe(this, Observer {
            Toast.makeText(this, R.string.connectionError, Toast.LENGTH_SHORT).show()
            progress_circular.visibility = View.GONE
        })
    }
}
