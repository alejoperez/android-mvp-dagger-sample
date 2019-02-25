package com.mvp.dagger.sample.data.photos

import android.content.Context
import com.mvp.dagger.sample.data.Photo
import com.mvp.dagger.sample.extensions.enqueue
import com.mvp.dagger.sample.webservice.IApi
import com.mvp.dagger.sample.webservice.WebService
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class PhotosRemoteDataSource @Inject constructor() : IPhotosDataSource {

    override fun savePhotos(photos: List<Photo>) = throw UnsupportedOperationException()

    override fun getPhotos(context: Context, listener: PhotosRepository.IPhotosListener) {
        val service = WebService.createService(context, IApi::class.java)
        val call = service.getPhotos()
        call.enqueue(
                { response ->
                    if (response.isSuccessful) {
                        listener.onPhotosSuccess(response.body())

                    } else {
                        listener.onPhotosFailure()
                    }
                },
                {
                    listener.onNetworkError()
                }
        )
    }

}