package com.mvp.dagger.sample.data.places

import android.content.Context
import com.mvp.dagger.sample.data.IBaseSourceListener
import com.mvp.dagger.sample.data.Place
import javax.inject.Inject
import javax.inject.Named

class PlacesRepository
@Inject
constructor(@Named(IBaseSourceListener.LOCAL) private val localDataSource: IPlacesDataSource,
            @Named(IBaseSourceListener.REMOTE) private val remoteDataSource: IPlacesDataSource) : IPlacesDataSource {


    private var hasCache = false

    override fun getPlaces(context: Context, listener: IPlacesListener) {
        if (hasCache) {
            localDataSource.getPlaces(context, listener)
        } else {
            remoteDataSource.getPlaces(context, object : IPlacesListener{

                override fun onPlacesSuccess(places: List<Place>?) {
                    if (places != null) {
                        savePlaces(places)
                        listener.onPlacesSuccess(places)
                    } else {
                        listener.onPlacesFailure()
                    }
                    listener.onPlacesSuccess(places)
                }

                override fun onPlacesFailure() = listener.onPlacesFailure()

                override fun onNetworkError() = listener.onNetworkError()
            })
        }
    }

    override fun savePlaces(places: List<Place>) {
        localDataSource.savePlaces(places)
    }

    fun invalidateCache() {
        hasCache = false
    }


    interface IPlacesListener : IBaseSourceListener {
        fun onPlacesSuccess(places: List<Place>?)
        fun onPlacesFailure()
    }
}