package com.mvp.dagger.sample.data.photos

import android.content.Context
import com.mvp.dagger.sample.data.IBaseSourceListener
import com.mvp.dagger.sample.data.Photo
import javax.inject.Inject
import javax.inject.Named

class PhotosRepository
@Inject
constructor(@Named(IBaseSourceListener.LOCAL) private val localDataSource: IPhotosDataSource,
            @Named(IBaseSourceListener.REMOTE) private val  remoteDataSource: IPhotosDataSource) : IPhotosDataSource {

    private var hasCache = false

    override fun getPhotos(context: Context, listener: IPhotosListener) {
        if (hasCache) {
            localDataSource.getPhotos(context, listener)
        } else {
            remoteDataSource.getPhotos(context, object : IPhotosListener{

                override fun onPhotosSuccess(photos: List<Photo>?) {
                    if (photos != null) {
                        savePhotos(photos)
                        listener.onPhotosSuccess(photos)
                    } else {
                        listener.onPhotosFailure()
                    }
                    listener.onPhotosSuccess(photos)
                }

                override fun onPhotosFailure() = listener.onPhotosFailure()

                override fun onNetworkError() = listener.onNetworkError()
            })
        }
    }

    override fun savePhotos(photos: List<Photo>) {
        localDataSource.savePhotos(photos)
    }

    fun invalidateCache() {
        hasCache = false
    }


    interface IPhotosListener : IBaseSourceListener {
        fun onPhotosSuccess(photos: List<Photo>?)
        fun onPhotosFailure()
    }
}