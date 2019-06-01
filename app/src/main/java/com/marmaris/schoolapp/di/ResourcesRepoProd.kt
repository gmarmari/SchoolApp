package com.marmaris.schoolapp.di

import android.content.Context
import com.marmaris.schoolapp.R

/**
 * Productive implementation of the ResourcesRepo Interface.
 * Gives out the real Resources of the app.
 **/
class ResourcesRepoProd(private val mContext: Context) : ResourcesRepo {

    //region strings

    override val stringOnlineMode: String
        get() = mContext.getString(R.string.online_mode)

    override val stringOnlineModeDescription: String
        get() = mContext.getString(R.string.online_mode_description)

    //endregion strings

    //region images

    override val imageWeb: Int
        get() = R.drawable.ic_web

    //endregion strings

}