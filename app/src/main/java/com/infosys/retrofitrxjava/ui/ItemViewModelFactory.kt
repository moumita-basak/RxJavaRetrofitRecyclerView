package com.infosys.retrofitrxjava.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infosys.retrofitrxjava.repositories.ItemRepository


@Suppress("UNCHECKED_CAST")
    class ItemViewModelFactory(
    private val repository: ItemRepository, val application: Application
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItemViewModel(repository,application) as T
        }
    }
