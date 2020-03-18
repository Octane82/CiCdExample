package com.everlapp.cicdexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * https://habr.com/post/328326/
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
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
            bundle.putString(EXTRA_MESSAGE, "This is a test")
            val intent = Intent(this, DisplayMessageActivity::class.java)
            intent.putExtras(bundle)

            startActivity(intent)
        }


        // Add fragment
        /*supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, UsersFragment())
                .addToBackStack(null)
                .commit()*/

    }
}
