// data/datasources/models/CourseDeleteResponse.kt
package com.kull18.edutrack.features.course_delete.data.datasources.models

import com.google.gson.annotations.SerializedName

data class CourseDeleteDTO(
    @SerializedName("message")
    val message: String
)