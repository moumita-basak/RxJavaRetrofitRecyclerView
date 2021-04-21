package com.infosys.retrofitrxjava.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.infosys.retrofitrxjava.AppManager
import com.infosys.retrofitrxjava.modelnew.Items

import com.infosys.retrofitrxjava.repositories.ItemRepository
import com.infosys.retrofitrxjava.util.ApiException
import com.infosys.retrofitrxjava.util.Coroutines
import com.infosys.retrofitrxjava.util.NoInternetException
import com.orhanobut.logger.Logger
import org.json.JSONObject
import java.lang.Exception

class ItemViewModel(private val repository: ItemRepository, application: Application) :
    AndroidViewModel(application) {
    var authListener: AuthListener? = null
    var applicationcontext: Application
    var itemPojo: MutableLiveData<Items> = MutableLiveData()

    init {
        this.applicationcontext = application
    }
    fun getItemNewList() {
        var requestMap = HashMap<String, String>()
        requestMap.put("auth","")
        authListener?.onBegin()
        Coroutines.main {
            try {
                val itemResponse = repository.getItemList(requestMap)
                Logger.e(itemResponse.toString())
                itemResponse.let {
                    var jObj = JSONObject(it.toString())
                    var data = ""
                    var gson = Gson()
                        itemPojo.value = gson.fromJson(jObj.toString(), Items::class.java)
                }

            }

        catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}