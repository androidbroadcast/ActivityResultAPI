package dev.androidbroadcast.activityresultapi.custom_contract

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.androidbroadcast.activityresultapi.R
import dev.androidbroadcast.activityresultapi.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity(R.layout.activity_display) {

    private val viewBinding by viewBinding(ActivityDisplayBinding::bind)

    private val getMessage = registerForActivityResult(EditMessageContract()) {
        viewBinding.message.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.getMessage.setOnClickListener {
            getMessage.launch(viewBinding.message.text)
        }
    }
}