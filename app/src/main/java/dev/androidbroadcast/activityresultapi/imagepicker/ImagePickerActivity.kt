package dev.androidbroadcast.activityresultapi.imagepicker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.clear
import coil.load
import dev.androidbroadcast.activityresultapi.R
import dev.androidbroadcast.activityresultapi.databinding.ImagePickerBinding

class ImagePickerActivity : AppCompatActivity(R.layout.image_picker) {

    private val viewBinding by viewBinding(ImagePickerBinding::bind)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.pickImage.setOnClickListener {
            pickImage.launch(MIMETYPE_IMAGES)
        }
    }
}