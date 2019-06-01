package com.marmaris.schoolapp.settings

import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.marmaris.schoolapp.R

class SettingsItemOnOff : SettingsItem {

    private var mTitle : String = ""
    override var title: String
        get() = mTitle
        set(value) {mTitle = value}

    private var mDescription : String = ""
    override var description: String
        get() =  mDescription
        set(value) { mDescription = value }

    private var mImageRes: Int? = null
    override var imageRes: Int?
        get() = mImageRes
        set(value) { mImageRes = value}

    var isOn : Boolean = false



    @BindView(R.id.m_image)
    lateinit var mImage: ImageView

    @BindView(R.id.m_textview_title)
    lateinit var mTextViewTitle: TextView

    @BindView(R.id.m_switch)
    lateinit var mSwitch: Switch

    @BindView(R.id.m_textview_description)
    lateinit var mTextViewDescription: TextView


    override fun setUpView(holder: RecyclerView.ViewHolder) {
        if (holder is SettingsRecyclerViewAdapter.OnOffViewHolder){
            ButterKnife.bind(this,  holder.itemView)
            mTextViewTitle.text = title
            if (imageRes != null) {
                mImage.setImageResource(imageRes!!)
                mImage.visibility = View.VISIBLE
            } else {
                mImage.visibility = View.GONE
            }

            mTextViewDescription.text = description
            mSwitch.isChecked = isOn
        }
    }



}