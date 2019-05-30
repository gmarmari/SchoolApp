package com.marmaris.schoolapp.lessons

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import com.marmaris.schoolapp.BaseFragment
import com.marmaris.schoolapp.R
import com.marmaris.schoolapp.data.lessons.Lesson
import kotlinx.android.synthetic.main.fragment_lessons.*
import java.lang.Error
import javax.inject.Inject

class LessonsFragment : BaseFragment() {

    //region Construction

    companion object{

        @JvmStatic
        fun newInstance() = LessonsFragment()

    }

    //endregion Construction

    @Inject
    lateinit var mViewModel: LessonsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_lessons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpRefreshLayout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.getLessons().observe(viewLifecycleOwner, Observer {
            setLessons(it)
        })
        mViewModel.isLoading().observe(viewLifecycleOwner, Observer {
            setRefreshing(it)
        })
        mViewModel.getError().observe(viewLifecycleOwner, Observer {
            if (it != null)
                alertError(it)
        })
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadLessons()
    }

    private fun setUpRefreshLayout() {
        m_swipe_refresh_layout_lessons.setOnRefreshListener {
            mViewModel.loadLessons()
        }
    }

    private fun setUpRecyclerView() {
        m_recycler_view_lessons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = LessonsRecyclerViewAdapter()
        }
    }

    private fun setLessons(value : List<Lesson>) {
        (m_recycler_view_lessons.adapter as? LessonsRecyclerViewAdapter)?.setLessons(value)
    }

    private fun setRefreshing(value : Boolean) {
        m_swipe_refresh_layout_lessons.isRefreshing = value
    }

    private fun alertError(error : Error){
        AlertDialog.Builder(activity)
            .setTitle("Error")
            .setMessage(error.toString())
            .setPositiveButton("Ok",({ _, _ ->  }))
            .show()
    }

}