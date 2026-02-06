package com.kull18.edutrack.features.course_detail.domain.repositories

import com.kull18.edutrack.features.course_detail.domain.entities.CourseDetial

interface CourseDetailRepository {
    suspend fun getCourseByIdDetail(id: Int): CourseDetial
}