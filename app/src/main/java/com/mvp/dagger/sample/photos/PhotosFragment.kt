package com.mvp.dagger.sample.photos

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvp.dagger.sample.R
import com.mvp.dagger.sample.base.BaseFragment
import com.mvp.dagger.sample.data.Photo
import com.mvp.dagger.sample.photos.detail.PhotoDetailDialogFragment
import com.mvp.dagger.sample.view.SimpleDividerItemDecorator
import kotlinx.android.synthetic.main.fragment_photos.*
import javax.inject.Inject

class PhotosFragment : BaseFragment(), IPhotosContract.View, PhotoItemView.OnPhotoClickListener {

    companion object {
        const val TAG = "PhotosFragment"
        fun newInstance() = PhotosFragment()
    }

    @Inject
    lateinit var presenter: IPhotosContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getPhotos()
    }

    override fun onPhotosSuccess(photos: List<Photo>?) {
        rvPhotos.apply {
            layoutManager = LinearLayoutManager(getViewContext())
            setHasFixedSize(true)
            addItemDecoration(SimpleDividerItemDecorator(getViewContext()))
            adapter = PhotosAdapter(photos, this@PhotosFragment)
        }
    }

    override fun onPhotosFailure() {
        showAlert(R.string.error_loading_photos)
    }

    override fun onPhotoClicked(photo: Photo?) {
        PhotoDetailDialogFragment.newInstance(photo?.url).show(fragmentManager, PhotoDetailDialogFragment.TAG)
    }

    override fun showProgress() {
        rvPhotos.visibility = View.INVISIBLE
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rvPhotos.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE
    }

}