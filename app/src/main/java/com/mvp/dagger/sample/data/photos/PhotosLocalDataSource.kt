package com.mvp.dagger.sample.data.photos

import android.content.Context
import com.mvp.dagger.sample.data.Photo
import io.realm.Realm
import javax.inject.Inject

class PhotosLocalDataSource @Inject constructor(): IPhotosDataSource {

    override fun savePhotos(photos: List<Photo>) {
        Realm.getDefaultInstance().executeTransactionAsync {
            realm -> realm.insertOrUpdate(photos)
        }
    }

    override fun getPhotos(context: Context, listener: PhotosRepository.IPhotosListener) {
        listener.onPhotosSuccess(Realm.getDefaultInstance().where(Photo::class.java).findAll())
    }

}