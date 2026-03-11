package com.kull18.edutrack.features.course_registration.data.datasources.models

import com.google.gson.annotations.SerializedName

data class EnrollCourseResponseDTO(
    @SerializedName("message")
    val message: String
)

