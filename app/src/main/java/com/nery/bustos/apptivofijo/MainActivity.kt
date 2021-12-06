package com.nery.bustos.apptivofijo

import android.os.Bundle
import android.widget.Toast
import com.nery.bustos.apptivofijo.base.BaseActivity
import com.nery.bustos.apptivofijo.databinding.ActivityMainBinding
import com.nery.bustos.apptivofijo.presentation.add.ui.AddFixedAssetFragment
import com.nery.bustos.apptivofijo.presentation.list.ui.FixedAssetListFragment

class MainActivity : BaseActivity(), FixedAssetListFragment.FixedAssetListFragmentHandler,
    AddFixedAssetFragment.AddFixedAssetFragmentHandler {

    companion object {
        private val TAG_LIST_FRAGMENT = FixedAssetListFragment::class.java.name
        private val TAG_ADD_FRAGMENT = AddFixedAssetFragment::class.java.name
    }

    private val container: Int = R.id.main_container

    override fun onCreate(savedInstanceState: Bundle?) {
        val mBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        addFragment(
            container, FixedAssetListFragment
                .newInstance(this), TAG_LIST_FRAGMENT
        )
    }

    override fun onBackPressed() {
        when (getCurrentFragment(container)) {
            is AddFixedAssetFragment -> returnToFragment(TAG_LIST_FRAGMENT, container)
            else -> finish()
        }

    }

    override fun goToAdd() {
        changeToFragment(
            container, AddFixedAssetFragment
                .newInstance(this), TAG_ADD_FRAGMENT
        )
    }

    override fun onAddSuccess() {
        returnToFragment(TAG_LIST_FRAGMENT, container)
        Toast.makeText(
            this,
            R.string.add_fixed_asset_success,
            Toast.LENGTH_LONG).show()
    }
}