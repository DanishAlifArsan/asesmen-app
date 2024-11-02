package com.example.asesmenpaud.activity

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.asesmenpaud.R
import com.example.asesmenpaud.data.ListPenilaianItem
import com.example.asesmenpaud.databinding.ActivityPenilaianCreateBinding
import com.example.asesmenpaud.databinding.ActivityPenilaianDetailBinding

class PenilaianCreateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPenilaianCreateBinding
    private var currentImageUri : Uri? = null
    private var speechText : ArrayList<String>? = null

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityPenilaianCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        val penilaian = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(PenilaianDetailActivity.PENILAIAN_KEY, ListPenilaianItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(PenilaianDetailActivity.PENILAIAN_KEY)
        }
        if (penilaian != null) {
            binding.title.text = getString(R.string.edit_penilaian)
            binding.edAddDescription.setText(penilaian.desc)
            Glide.with(this)
                .load(penilaian.photoUrl)
                .into(binding.ivPhoto)
        } else {
            binding.title.text = getString(R.string.tambah_penilaian)
        }

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener {    // buat save ke database
            finish()
        }

        binding.ivPhoto.setOnClickListener{
            startCamera()
        }

        binding.btnMicrophone.setOnClickListener{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "id")

            try {
                launcherIntentSpeech.launch(i)
            } catch (e : ActivityNotFoundException) {
                Toast.makeText(this, "Tidak support speech to text.", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun startCamera() {
        val i = Intent(this, CameraActivity::class.java)
        launcherIntentCamera.launch(i)
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.ivPhoto.setImageURI(it)
        }
    }

    private fun showText() {
        speechText?.let {
            binding.edAddDescription.setText(it.get(0))
        }
    }

    private val launcherIntentSpeech = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            speechText = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            showText()
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CameraActivity.CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    companion object{
        const val PENILAIAN_KEY = "penilaian_key"
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}