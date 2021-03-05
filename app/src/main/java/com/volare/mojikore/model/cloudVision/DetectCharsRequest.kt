package com.volare.mojikore.model.cloudVision

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity
@JsonClass(generateAdapter = true)
data class Image(
    val content: String
)

@Entity
@JsonClass(generateAdapter = true)
data class Feature(
    val type: String = "TEXT_DETECTION"
)

@Entity
@JsonClass(generateAdapter = true)
data class Request(
    val image: Image,
    val features: List<Feature> = listOf(Feature()),
)

@Entity
@JsonClass(generateAdapter = true)
data class DetectCharsRequest(
    val requests: List<Request>
)