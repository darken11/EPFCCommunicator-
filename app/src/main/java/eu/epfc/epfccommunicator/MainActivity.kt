package eu.epfc.epfccommunicator


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1 //random number id

     private var imageBitmap=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSendButtonClicked(view: View) {
        val messageEditText: EditText = findViewById(R.id.editText)
        val editTextString = messageEditText.text.toString()
        val context: Context = this
        val intent = Intent(context, ReceiveMessageActivity::class.java)

        intent.putExtra(Intent.EXTRA_TEXT, editTextString)
        startActivity(intent)

    }

    fun onShareButtonClicked(view: View) {
        val messageEditText: EditText = findViewById(R.id.editText)

        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        val editTextString = messageEditText.text.toString()
        intent.putExtra(Intent.EXTRA_TEXT, editTextString)

        startActivity(intent)
    }

    fun showMap(view: View) {
        val searchText: EditText = findViewById(R.id.searchText)
        val searchLocal = searchText.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$searchLocal")
        onShowMap(addressUri)

    }

    fun onShowMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {

            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openCam(view: View) {
        val imageView: ImageView = findViewById(R.id.imageView)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
        // modern way, using lambda function
//            val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//
//                if (it.resultCode == RESULT_OK){
//                    if (it.data != null) {
//                        // extract the image from the intent
//                        val extras = it.data.extras
//                        if (extras != null) {
//                            imageBitmap = extras.get("data") as Bitmap
//
//                            // display the image in the ImageView
//                            val cameraThumbnailImageView: ImageView =
//                                findViewById(R.id.cameraThumbnailImageView)
//                            cameraThumbnailImageView.setImageBitmap(imageBitmap)
//                        }
//                    }
//                }
//            }
//            activityResultLauncher.launch(takePictureIntent)
   // }
//}
//

}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val extras: Bundle? = intent.extras
        if(extras !=null) {

            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = extras.get("data") as Bitmap
                val imageView: ImageView = findViewById(R.id.imageView)
                imageView.setImageBitmap(imageBitmap)
            }
        }
    }
    fun onImageViewClicked(view: View) {

        val myImageBitmap : Bitmap? = imageBitmap
        if (myImageBitmap != null) {

            //Convert Bitmap to byte array
            val stream = ByteArrayOutputStream()
            myImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            // create an explicit Intent with class ReceiveMessageActivity
            val intent = Intent(this, FullImageActivity::class.java)
            // add the image to the intent
            intent.putExtra("image", byteArray)
            // start the activity FullImageActivity
            startActivity(intent)
        }
    }

}