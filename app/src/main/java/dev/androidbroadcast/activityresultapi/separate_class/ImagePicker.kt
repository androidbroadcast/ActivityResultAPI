package dev.androidbroadcast.activityresultapi.separate_class

import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import dev.androidbroadcast.activityresultapi.imagepicker.MIMETYPE_IMAGES

class ImagePicker(
    registry: ActivityResultRegistry,
    private val owner: LifecycleOwner,
    private val callback: (imageUri: Uri?) -> Unit
) {

    private var getContent: ActivityResultLauncher<String> =
        registry.register(RESULT_REGISTRY_KEY, owner, ActivityResultContracts.GetContent(), callback)

    fun selectImage() {
        if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.w(TAG, "Linked LifecycleOwner is destroyed")
            return
        }

        getContent.launch(MIMETYPE_IMAGES)
    }

    private companion object {

        const val TAG = "ImagePicker"
        const val RESULT_REGISTRY_KEY = "pick_image"
    }
}