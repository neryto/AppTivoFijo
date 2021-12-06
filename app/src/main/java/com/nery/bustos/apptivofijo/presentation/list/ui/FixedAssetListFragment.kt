package com.nery.bustos.apptivofijo.presentation.list.ui

import android.view.View
import com.nery.bustos.apptivofijo.presentation.list.FixedAssetListActions
import com.nery.bustos.apptivofijo.R
import com.nery.bustos.apptivofijo.base.BaseFragment
import com.nery.bustos.apptivofijo.presentation.list.view.FixedAssetListView

class FixedAssetListFragment
    : BaseFragment() {

    lateinit var mView: FixedAssetListView
    lateinit var fragmentHandler: FixedAssetListFragmentHandler

    interface FixedAssetListFragmentHandler {
        fun goToAdd()
    }

    companion object {
        fun newInstance(fragmentHandler: FixedAssetListFragmentHandler): FixedAssetListFragment =
            FixedAssetListFragment().apply {
                this.fragmentHandler = fragmentHandler
            }
    }

    override fun setLayout(): Int = R.layout.fragment_fixed_asset_list

    override fun setupView(view: View) {
        mView = FixedAssetListView(actionsHandler, requireContext()).apply {
            setupViewBinding(view)
            init(this@FixedAssetListFragment)
        }
    }

    private val actionsHandler: (action: FixedAssetListActions, value: Any?) -> Unit =
        { fixedAssetListActions: FixedAssetListActions, _: Any? ->
            when (fixedAssetListActions) {
                FixedAssetListActions.GO_TO_ADD -> fragmentHandler.goToAdd()
            }

        }
}