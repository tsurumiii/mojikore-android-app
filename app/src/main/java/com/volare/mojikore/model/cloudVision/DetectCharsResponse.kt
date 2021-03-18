package com.volare.mojikore.model.cloudVision

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class TextAnnotation(
    @field:Json(name = "description") val description: String
)

@Entity
@JsonClass(generateAdapter = true)
data class Response(
    @field:Json(name = "textAnnotations") val textAnnotations: List<TextAnnotation>
)

@JsonClass(generateAdapter = true)
data class DetectCharsResponse(
    @field:Json(name = "responses") val responses: List<Response>
)