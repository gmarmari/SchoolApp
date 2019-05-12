package com.marmaris.schoolapp.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marmaris.schoolapp.R
import com.marmaris.schoolapp.data.lessons.Lesson

class LessonsRecyclerViewAdapter() : RecyclerView.Adapter<LessonsRecyclerViewAdapter.LessonsViewHolder>() {

    private var mLessons: List<Lesson> = ArrayList()

    fun setLessons(lessons : List<Lesson>){
        mLessons = lessons
        notifyDataSetChanged()
    }

    class LessonsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mTextView: TextView = view.findViewById(R.id.m_text_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val viewGroup = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_lesson, parent, false)
        return LessonsViewHolder(viewGroup)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.mTextView.text = mLessons[position].mTitle
    }

    override fun getItemCount(): Int {
       return mLessons.size
    }



}