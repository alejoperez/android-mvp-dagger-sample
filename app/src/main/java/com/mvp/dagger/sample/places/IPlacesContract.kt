package com.mvp.dagger.sample.places

import com.mvp.dagger.sample.base.IBaseView
import com.mvp.dagger.sample.data.Place

interface IPlacesContract {

    interface View: IBaseView {
        fun onPlacesSuccess(places: List<Place>?)
        fun onPlacesFailure()
    }

    interface Presenter {
        fun getPlaces()
    }
}