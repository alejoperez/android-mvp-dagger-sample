package com.mvp.dagger.sample.photos

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.mvp.dagger.sample.data.Photo

class PhotosAdapter(private val photoList: List<Photo>?, val listener: PhotoItemView.OnPhotoClickListener) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val photoItemView = PhotoItemView(parent.context)
        photoItemView.setOnPhotoClickListener(listener)
        return ViewHolder(photoItemView)
    }

    override fun getItemCount(): Int = photoList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(photoList?.get(position))

    class ViewHolder(private val photoItemView: PhotoItemView) : RecyclerView.ViewHolder(photoItemView) {
        fun bind(photo: Photo?) = photoItemView.bind(photo)
    }

}