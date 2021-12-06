package com.nery.bustos.apptivofijo.presentation.add.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.nery.bustos.apptivofijo.R
import com.nery.bustos.apptivofijo.base.BaseFragment
import com.nery.bustos.apptivofijo.presentation.add.AddFixedAssetActions
import com.nery.bustos.apptivofijo.presentation.add.view.AddFixedAssetView

class AddFixedAssetFragment : BaseFragment() {

    companion object {
        fun newInstance(fragmentHandler: AddFixedAssetFragmentHandler):
                AddFixedAssetFragment = AddFixedAssetFragment().apply {
            this.fragmentHandler = fragmentHandler
        }
    }

    interface AddFixedAssetFragmentHandler {
        fun onAddSuccess()
    }

    private lateinit var fragmentHandler: AddFixedAssetFragmentHandler
    lateinit var mView: AddFixedAssetView

    override fun setLayout(): Int = R.layout.fragment_add_fixed_asset

    override fun setupView(view: View) {
        mView = AddFixedAssetView(actionHandler).apply {
            setupViewBinding(view)
            init(this@AddFixedAssetFragment)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted)
                takePhoto()
            else showMessageCameraPermissions()
        }

    private val requestTakePhotoLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK)
                result.data?.let { intent ->
                    intent.extras?.let {
                        val bm = it.get("data") as Bitmap
                        mView.onTakePhoto(bm)
                    }
                }
        }


    private fun showMessageCameraPermissions() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.request_permissions)
                setMessage(R.string.need_camera_permissions)
                setCancelable(false)
                setPositiveButton(
                    R.string.accept
                ) { dialog, _ ->
                    dialog.dismiss()
                }


            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun takePhoto() {
        requestTakePhotoLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun validateCameraPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                takePhoto()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showMessageCameraPermissionsToConfigurations()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }

    }

    private fun showMessageCameraPermissionsToConfigurations() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.request_permissions)
                setMessage(R.string.need_camera_permissions)
                setCancelable(false)
                setPositiveButton(
                    R.string.accept
                ) { dialog, _ ->
                    dialog.dismiss()
                    //request permissions
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )

                }
            }
            builder.create()
        }
        alertDialog?.show()

    }

    private val actionHandler: (action: AddFixedAssetActions, value: Any?) -> Unit =
        { addFixedAssetActions: AddFixedAssetActions, any: Any? ->
            when (addFixedAssetActions) {
                AddFixedAssetActions.TAKE_PHOTO -> validateCameraPermissions()
                AddFixedAssetActions.PHOTO_ERROR -> Toast.makeText(
                    requireContext(),
                    R.string.need_photo_data, Toast.LENGTH_LONG
                ).show()
                AddFixedAssetActions.TYPE_ERROR -> Toast.makeText(
                    requireContext(),
                    R.string.need_type_data, Toast.LENGTH_LONG
                ).show()
                AddFixedAssetActions.SUCCESS -> {
                    fragmentHandler.onAddSuccess()
                }
            }
        }

}