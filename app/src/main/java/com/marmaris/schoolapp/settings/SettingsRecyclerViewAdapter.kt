package com.marmaris.schoolapp.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marmaris.schoolapp.R

class SettingsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class OnOffViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object{
            fun newInstance(parent: ViewGroup) : OnOffViewHolder{
                val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.recycler_settings_on_off, parent, false)
                return OnOffViewHolder(viewGroup)
            }
        }

    }

    private var mItems: List<SettingsItem> = ArrayList()

    fun setItems(items : List<SettingsItem>){
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OnOffViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mItems[position].setUpView(holder)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

}