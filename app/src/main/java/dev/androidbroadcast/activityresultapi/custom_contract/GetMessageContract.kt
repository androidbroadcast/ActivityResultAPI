package dev.androidbroadcast.activityresultapi.custom_contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class GetMessageContract : ActivityResultContract<CharSequence?, CharSequence?>() {

    override fun createIntent(context: Context, input: CharSequence?): Intent {
        return Intent(context, MessageGeneratorActivity::class.java)
            .putExtra(EXTRA_MESSAGE, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): CharSequence? {
        intent ?: return null
        if (resultCode != Activity.RESULT_OK) return null

        return intent.getCharSequenceExtra(EXTRA_MESSAGE)
    }

    override fun getSynchronousResult(context: Context, input: CharSequence?): SynchronousResult<CharSequence?>? {
        return super.getSynchronousResult(context, input)
    }
}