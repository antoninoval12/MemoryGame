package com.antonino.memorygame
data class MemoryCard(
    //Identificativo dell'immagine associata alla carta.
    val identifier: Int,
    //Indica se la carta è attualmente scoperta (true) o coperta (false).
    var isFaceUp: Boolean = false,
    //Indica se la carta è stata correttamente abbinata con la sua coppia.
    var isMatched: Boolean = false
    )

