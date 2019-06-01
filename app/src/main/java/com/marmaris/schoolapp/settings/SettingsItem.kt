package com.marmaris.schoolapp.settings

import androidx.recyclerview.widget.RecyclerView

interface SettingsItem {

    var title : String
    var description : String
    var imageRes : Int?

    fun setUpView(holder: RecyclerView.ViewHolder)

}