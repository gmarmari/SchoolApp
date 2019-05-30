package com.marmaris.schoolapp.retrofit

import retrofit2.Response
import java.lang.Error

class ResponseHelper<T>(private val mResponse: Response<T>?) {

    companion object{
        private const val NEW_LINE = "\n"
    }

    fun getError() : Error? {
        if (mResponse?.body() != null)
            return null
        if (mResponse == null) {
            return Error("mResponse == null")
        }

        var text = ""
        /*
        if (mResponse.errorBody() != null) {
            if (mResponse.errorBody().bytes() != null) {
                if (text.isNotEmpty())
                    text += NEW_LINE
                text += "ErrorBody: " + String(mResponse.errorBody().bytes())
            }
        }
        */
        if (mResponse.raw() != null) {
            if (text.isNotEmpty())
                text += NEW_LINE
            text += "Raw Response: "
            text += NEW_LINE
            text += "Code: " + mResponse.raw().code()
            text += NEW_LINE
            text += "Message: " + mResponse.raw().message()
            text += NEW_LINE
            text += "Network response: " + mResponse.raw().networkResponse()
        }
        return Error(text)
    }


}