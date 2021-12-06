package com.nery.bustos.apptivofijo.presentation.list.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nery.bustos.apptivofijo.util.BitmapUtil.toBitmap
import com.nery.bustos.apptivofijo.R
import com.nery.bustos.apptivofijo.databinding.FixedAssetItemBinding
import com.nery.bustos.apptivofijo.presentation.list.FixedAsset

class FixedAssetListAdapter
constructor(private val list: List<FixedAsset>) :
    RecyclerView.Adapter<FixedAssetListAdapter.FixedAssetListViewHolder>() {


    class FixedAssetListViewHolder(
        private val binding: FixedAssetItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fixedAsset: FixedAsset) {
            binding.apply {
                tvName.text = fixedAsset.name
                tvDate.text = context.getString(R.string.acquisition, fixedAsset.date)
                tvDescription.text = fixedAsset.description
                tvType.text = context.getString(R.string.type, fixedAsset.type)
                imageView2.setImageBitmap(fixedAsset.photo.toBitmap())
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixedAssetListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context).inflate(R.layout.fixed_asset_item, parent, false)
        val mBinding = FixedAssetItemBinding.bind(view)
        return FixedAssetListViewHolder(mBinding, parent.context)

    }

    override fun onBindViewHolder(holder: FixedAssetListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}