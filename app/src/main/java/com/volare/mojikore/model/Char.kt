package com.volare.mojikore.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Char(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "img_count") val imgCount: String,
        @field:Json(name = "discovery_rate") val discoveryRate: String,
        @field:Json(name = "img_ids") val imgIds: String,
)
