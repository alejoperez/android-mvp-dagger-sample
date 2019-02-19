package com.mvp.dagger.sample.data.places

import android.content.Context
import com.mvp.dagger.sample.data.Place

interface IPlacesDataSource {

    fun getPlaces(context: Context, listener: PlacesRepository.IPlacesListener)

    fun savePlaces(places: List<Place>)
}