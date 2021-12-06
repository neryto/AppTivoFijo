package com.nery.bustos.apptivofijo.presentation.list.view

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nery.bustos.apptivofijo.base.BaseView
import com.nery.bustos.apptivofijo.databinding.FragmentFixedAssetListBinding
import com.nery.bustos.apptivofijo.presentation.list.FixedAssetListActions
import com.nery.bustos.apptivofijo.presentation.list.ui.FixedAssetListAdapter
import com.nery.bustos.apptivofijo.presentation.list.viewmodel.FixedAssetListViewModel

class FixedAssetListView
constructor(actionHandler: (action: FixedAssetListActions, value: Any?) -> Unit,private val context: Context) :
    BaseView<FragmentFixedAssetListBinding, FixedAssetListActions>(actionHandler) {

    private lateinit var viewModel: FixedAssetListViewModel

    override fun init(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        viewModel = ViewModelProvider(this)[FixedAssetListViewModel::class.java]
        super.init(lifecycleOwner)
        mBinding.apply {
            fab.setOnClickListener {
                actionHandler.invoke(FixedAssetListActions.GO_TO_ADD, null)
            }
        }
    }

    override fun setupViewBinding(view: View): FragmentFixedAssetListBinding {
        binding = FragmentFixedAssetListBinding.bind(view)
        return binding as FragmentFixedAssetListBinding
    }

    override fun initObservers() {
        lifecycleOwner.lifecycle.addObserver(this)
        viewModel.fetchInfo.observe(lifecycleOwner, {
            mBinding.apply {
                tvMessage.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                recycler.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
                recycler.adapter = FixedAssetListAdapter(it)
                recycler.layoutManager = LinearLayoutManager(context)
            }
        })
    }


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.getList()

    }

}