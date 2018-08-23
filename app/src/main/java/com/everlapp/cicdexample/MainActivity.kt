package com.everlapp.cicdexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * https://habr.com/post/328326/
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRTA_MESSAGE = "extra_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Change text in header
        btnChangeText.setOnClickListener{
            textToBeChanged.text = editTextUserInput.text
        }

        // Send intent to another activity
        btnSendMessage.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(EXTRTA_MESSAGE, "This is a test")
            val intent = Intent(this, DisplayMessageActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }

    }
}
