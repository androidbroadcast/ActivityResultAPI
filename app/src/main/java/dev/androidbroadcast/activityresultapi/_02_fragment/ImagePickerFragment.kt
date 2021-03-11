package dev.androidbroadcast.activityresultapi._02_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import dev.androidbroadcast.activityresultapi.R
import dev.androidbroadcast.activityresultapi.databinding.ImagePickerBinding
import dev.androidbroadcast.activityresultapi._01_activity.MIMETYPE_IMAGES

class ImagePickerFragment : Fragment(R.layout.image_picker) {

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { contentUri ->
        with(viewBinding.image) {
            clear()
            load(contentUri) {
                listener(
                    onError = { request, throwable ->
                        Toast.makeText(request.context, throwable.message, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

    private val viewBinding by viewBinding(ImagePickerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.pickImage.setOnClickListener {
            pickImage.launch(MIMETYPE_IMAGES)
        }
    }
}

