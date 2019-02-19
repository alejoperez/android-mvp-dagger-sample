package com.mvp.dagger.sample.photos.detail

import android.annotation.SuppressLint
import com.mvp.dagger.sample.base.BaseDialogFragment
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.mvp.dagger.sample.R
import javax.inject.Inject

class PhotoDetailDialogFragment : BaseDialogFragment() {

    @Inject
    lateinit var photoUrl: String

    companion object {
        const val TAG = "PhotoDetailDialogFragment"
        const val PHOTO_URL = "PhotoDetailDialogFragment.PHOTO_URL"

        fun newInstance(photoUrl: String?) = PhotoDetailDialogFragment().apply {
            arguments = Bundle().apply {
                putString(PHOTO_URL, photoUrl)
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(getViewContext()).inflate(R.layout.dialog_photo_detail, null)

        Glide.with(this).load(photoUrl).into(view.findViewById(R.id.ivPhotoDetail))

        return AlertDialog.Builder(activity).apply {
            setView(view).setPositiveButton(R.string.accept) { _, _ -> dialog.cancel() }
        }.create()
    }

}