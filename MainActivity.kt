package com.antonino.memorygame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val images = mutableListOf(
            R.drawable.ic_heart,
            R.drawable.ic_light,
            R.drawable.ic_plane,
            R.drawable.ic_smiley
        )

        // Aggiungi le immagini una seconda volta per creare le coppie
        images.addAll(images)
        images.shuffle() // Mescola le immagini

        // Inizializza i pulsanti (assicurati che questi ID esistano nel layout XML)
        buttons = listOf(
            findViewById(R.id.imageButton1),
            findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3),
            findViewById(R.id.imageButton4),
            findViewById(R.id.imageButton5),
            findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7),
            findViewById(R.id.imageButton8)
        )

        buttons.forEachIndexed{ index, button ->
            button.setOnClickListener{ it:View!
                Log.i(TAG, msg: "button clicked!!")
                button.setImageResource(images[index])
    }
}
