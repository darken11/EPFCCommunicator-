package eu.epfc.epfccommunicator

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FullImageActivity : AppCompatActivity(){



    // this variable specify if the imageView is in CENTER_CROP mode or FIT_CENTER mode
    private var isInFillMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val intent = intent

        if (intent.hasExtra("image")) {
            // get byte array from the intent
            val byteArray : ByteArray? = intent.getByteArrayExtra("image")

            if (byteArray != null) {
                // decode the byte array into a Bitmap
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

                // set the bitmap to the imageView
                val imageView: ImageView = findViewById(R.id.full_imageview)
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    fun onImageViewClicked(view: View) {
        isInFillMode = !isInFillMode
        val imageView = view as ImageView
        if (isInFillMode) {
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }
}