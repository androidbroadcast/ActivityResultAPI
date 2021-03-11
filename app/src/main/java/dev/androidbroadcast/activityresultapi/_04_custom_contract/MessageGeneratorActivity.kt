package dev.androidbroadcast.activityresultapi._04_custom_contract

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.androidbroadcast.activityresultapi.R
import dev.androidbroadcast.activityresultapi.databinding.ActivityMessageGeneratorBinding

class MessageGeneratorActivity : AppCompatActivity(R.layout.activity_message_generator) {

    private val viewBinding by viewBinding(ActivityMessageGeneratorBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            viewBinding.message.setText(intent.getCharSequenceExtra(EXTRA_MESSAGE))
            send.setOnClickListener {
                sendResult()
            }
        }
    }

    private fun sendResult() {
        val data = Intent().putExtra(EXTRA_MESSAGE, viewBinding.message.text as CharSequence)
        setResult(RESULT_OK, data)
        finish()
    }
}