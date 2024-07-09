package com.example.checkcheck.lecture.repository

import com.example.checkcheck.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LectureRepository : JpaRepository<Lecture, Long> {
    fun findByTitle(title : String) : Lecture?

    @Query(value = "SELECT l FROM Lecture l LEFT JOIN FETCH l.resisterPeriod LEFT JOIN FETCH l.lectureSchedule")
    fun findAllByFetchJoin() : List<Lecture>
}