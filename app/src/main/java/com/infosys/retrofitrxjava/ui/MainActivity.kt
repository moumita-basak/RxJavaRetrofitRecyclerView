package com.infosys.retrofitrxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infosys.retrofitrxjava.Adapter.CustomAdapter
import com.infosys.retrofitrxjava.R
import com.infosys.retrofitrxjava.databinding.ActivityMainBinding
import com.infosys.retrofitrxjava.modelnew.ItemRow
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(),AuthListener,KodeinAware{
    override val kodein by kodein()
    lateinit var itemViewModel: ItemViewModel
    val factory: ItemViewModelFactory by instance()
    lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter
    lateinit var itemList: MutableList<ItemRow>
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        itemViewModel = ViewModelProviders.of(this,factory).get(ItemViewModel::class.java)
        binding.itemViewModel = itemViewModel
        itemViewModel.authListener = this

        initCode()
        eventListener()
    }

    private fun eventListener() {
        itemViewModel.itemPojo.observe(this@MainActivity, androidx.lifecycle.Observer {
            var ss= it.rows
            itemList = it.rows
            layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.recyclerView.layoutManager = layoutManager
            customAdapter= CustomAdapter(this, itemList)
            binding.recyclerView.adapter = customAdapter

        })
    }

    private fun initCode() {
        itemViewModel.getItemNewList()

    }

    override fun onBegin() {
    }

    override fun onSuccess(value: String) {
    }

    override fun onFailure(value: String) {
    }

    override fun validationFail(value: String?, field: String?) {
    }
}


