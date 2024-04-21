package br.edu.projetofinalkotlin.view.takepicture

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import br.edu.projetofinalkotlin.view.login.CreateAccountActivity
import java.io.File

class TakePictureActivity : AppCompatActivity() {
    private lateinit var image: File
    private lateinit var filename: String
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeCamera()
    }

    private fun initializeCamera() {
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    sendUserPicture(image.toUri())
                } else {
                    Log.e(TAG, "Error: Image capture failed")
                }
            }
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        image = createImageFile()
        val photoURI =
            FileProvider.getUriForFile(this, "br.edu.projetofinalkotlin", image)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        launcher.launch(intent)
    }

    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile(
            "JPEG_${System.currentTimeMillis()}_",
            ".jpg",
            storageDir
        ).apply {
            filename = absolutePath
        }
    }

    private fun sendUserPicture(uri: Uri) {
        val intent = Intent(this, CreateAccountActivity::class.java)
        intent.putExtra("userPicture", uri)
        startActivity(intent)
    }
}
