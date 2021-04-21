package com.infosys.retrofitrxjava.ui

interface AuthListener {
    fun onBegin()
    fun onSuccess(value:String)
    fun onFailure(value: String)
    fun validationFail(value: String?, field: String?)
}