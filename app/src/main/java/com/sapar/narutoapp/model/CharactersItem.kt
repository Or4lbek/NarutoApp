package com.sapar.narutoapp.model

data class CharactersItem(
    val age: Int,
    val best_jutsus: List<String>,
    val birthdate: String,
    val clan: String,
    val classifaction: List<String>,
    val firstName: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val occupation: List<String>,
    val village: String
)