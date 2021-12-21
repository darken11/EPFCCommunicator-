package eu.epfc.epfccommunicator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReceiveMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)
        // get the text out of the intent
        val textToDisplay = intent.getStringExtra(Intent.EXTRA_TEXT)

        // set the text to the TextView
        val textView : TextView = findViewById(R.id.receive_textView)
        textView.text = textToDisplay
    }
}