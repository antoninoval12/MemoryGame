package com.antonino.memorygame

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.baseline_bolt_24,
            R.drawable.baseline_beach_access_24,
            R.drawable.baseline_attach_money_24,
            R.drawable.baseline_diamond_24
        )
        val randomizedImages = (images + images).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }

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

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked at $index")
                updateModels(index)
                updateViews()
            }
        }
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(
                if (card.isFaceUp) card.identifier
                else R.drawable.baseline_fingerprint_24 // immagine card di default
            )
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]

        if (card.isFaceUp) {
            Toast.makeText(this, "mossa non valida", Toast.LENGTH_SHORT).show()
            return
        }

        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }

        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(this, "Coppia Trovata", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
    }
}
