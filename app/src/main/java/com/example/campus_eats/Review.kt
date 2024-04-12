package com.example.campus_eats

data class Review(
    val reviewId: String,
    val userId: String,
    val username: String,
    val location: String,
    val rating: Float,
    val reviewText: String
) {
    constructor() : this("", "", "", "", 0.0f, "")
}
