package com.volare.mojikore.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Image (
        val id: Int,
        val date: String,
        val latitude: Double,
        val longitude: Double,
        val place: String,
        @field:Json(name = "img_url") val imgUrl: String,
        @field:Json(name = "char_names") val charNames: String,
        val chars: List<Char>
)