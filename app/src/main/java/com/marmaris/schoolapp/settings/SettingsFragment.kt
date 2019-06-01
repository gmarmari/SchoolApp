package com.marmaris.schoolapp.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marmaris.schoolapp.BaseFragment
import com.marmaris.schoolapp.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    //region Construction

    companion object{

        @JvmStatic
        fun newInstance() = SettingsFragment()

    }

    //endregion Construction

    @Inject
    lateinit var viewModel: SettingsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getListItems().observe(viewLifecycleOwner, Observer {
            setSettingsItems(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.createListItems()
    }

    private fun setUpRecyclerView() {
        m_recycler_view_settings.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = SettingsRecyclerViewAdapter()
        }
    }

    private fun setSettingsItems(items : List<SettingsItem>) {
        (m_recycler_view_settings.adapter as? SettingsRecyclerViewAdapter)?.setItems(items)
    }

}