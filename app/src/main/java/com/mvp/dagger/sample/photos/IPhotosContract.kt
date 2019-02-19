package com.mvp.dagger.sample.photos

import com.mvp.dagger.sample.base.IBaseView
import com.mvp.dagger.sample.data.Photo

interface IPhotosContract {

    interface View: IBaseView {
        fun onPhotosSuccess(photos: List<Photo>?)
        fun onPhotosFailure()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun getPhotos()
    }
}