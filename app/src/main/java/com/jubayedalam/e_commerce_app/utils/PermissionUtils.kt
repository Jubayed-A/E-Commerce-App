package com.jubayedalam.e_commerce_app.utils

import android.Manifest
import android.os.Build


class PermissionUtils {

    companion object {
        fun permission() : Array<String>{
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf<String>(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_AUDIO
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayOf<String>(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            } else {
                arrayOf<String>(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            }
        }

    }

}