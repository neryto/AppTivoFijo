package com.nery.bustos.apptivofijo.presentation.add.view

import android.graphics.Bitmap
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.nery.bustos.apptivofijo.util.BitmapUtil.toBase64
import com.nery.bustos.apptivofijo.util.EditTextValidator.validate
import com.nery.bustos.apptivofijo.base.BaseView
import com.nery.bustos.apptivofijo.databinding.FragmentAddFixedAssetBinding
import com.nery.bustos.apptivofijo.presentation.list.ui.DateDialog
import com.nery.bustos.apptivofijo.presentation.add.AddFixedAssetActions
import com.nery.bustos.apptivofijo.presentation.add.FixedAssetType
import com.nery.bustos.apptivofijo.presentation.add.viewmodel.AddFixedAssetViewModel


class AddFixedAssetView
constructor(actionHandler: (action: AddFixedAssetActions, value: Any?) -> Unit) :
    BaseView<FragmentAddFixedAssetBinding, AddFixedAssetActions>(actionHandler) {

    private var bitmap: Bitmap? = null
    private var fixedAssetType: FixedAssetType? = null
    lateinit var viewModel : AddFixedAssetViewModel

    override fun init(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        super.init(lifecycleOwner)
        viewModel = ViewModelProvider(this)[AddFixedAssetViewModel::class.java]
        mBinding.apply {
            type.apply {
                adapter = ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_item,
                    FixedAssetType.values()
                )

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        fixedAssetType = FixedAssetType.values()[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        fixedAssetType = null
                    }
                }
                edtDate.setOnClickListener {
                    DateDialog(context) {
                        edtDate.setText(it)
                    }
                }
            }


            fabTakePhoto.setOnClickListener {
                actionHandler.invoke(AddFixedAssetActions.TAKE_PHOTO, null)
            }

            btnSave.setOnClickListener {
                if (validate(edtDate, edtDescription, edtName) &&
                    bitmap != null &&
                    fixedAssetType != null
                ) {
                    viewModel.saveData(
                        bitmap!!.toBase64(),
                        edtName.text.toString(),
                        fixedAssetType!!.value,
                        edtDescription.text.toString(),
                        edtDate.text.toString()
                    )
                    actionHandler.invoke(AddFixedAssetActions.SUCCESS,null)
                } else if (bitmap == null)
                    actionHandler.invoke(AddFixedAssetActions.PHOTO_ERROR, null)
                else if (fixedAssetType == null)
                    actionHandler.invoke(AddFixedAssetActions.TYPE_ERROR, null)
            }
        }
    }


    override fun initObservers() {
    }


    override fun setupViewBinding(view: View): FragmentAddFixedAssetBinding {
        binding = FragmentAddFixedAssetBinding.bind(view)
        return binding as FragmentAddFixedAssetBinding
    }

    fun onTakePhoto(bm: Bitmap) {
        this.bitmap = bm
        mBinding.imageView.setImageBitmap(bitmap)
    }
}