package com.example.checkcheck.lecture.dto

import com.example.checkcheck.common.annotation.ValidEnum
import com.example.checkcheck.common.enums.WeekDay
import com.example.checkcheck.lecture.entity.LectureSchedule
import com.example.checkcheck.lecture.entity.RegisterPeriod
import com.example.checkcheck.member.entity.Member
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class LectureRequestDto(
    @field:NotBlank(message = "강의명을 입력해주세요.")
    @JsonProperty("title")
    private var _title: String?,


    @field:NotBlank(message = "수강신청 시작시간을 입력해주세요.")
    @field:Pattern(
        regexp = "^([12]\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\s([01]\\d|2[0-4]):([0-5]\\d|60)\$",
        message = "시간 형식을 확인해주세요! yyyy-MM-dd HH:mm"
    )
    @JsonProperty("registerStartAt")
    private var _registerStartAt: String?,


    @field:NotBlank(message = "수강신청 종료시간을 입력해주세요.")
    @field:Pattern(
        regexp = "^([12]\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\s([01]\\d|2[0-4]):([0-5]\\d|60)\$",
        message = "시간 형식을 확인해주세요! yyyy-MM-dd HH:mm"
    )
    @JsonProperty("registerEndAt")
    private var _registerEndAt: String?,


    @field:NotBlank(message = "강의 시작시간을 입력해주세요.")
    @field:Pattern(
        regexp = "^([0-1]\\d|2[0-4]):([0-5]\\d|60)\$",
        message = "시간 형식을 확인해주세요! HH:mm"
    )
    @JsonProperty("lectureStartAt")
    private var _lectureStartAt: String?,


    @field:NotBlank(message = "강의 종료시간을 입력해 주세요.")
    @field:Pattern(
        regexp = "^([0-1]\\d|2[0-4]):([0-5]\\d|60)\$",
        message = "시간 형식을 확인해주세요! HH:mm"
    )
    @JsonProperty("lectureEndAt")
    private var _lectureEndAt: String?,


    @field:NotBlank(message = "강의요일을 입력해 주세요.")
    @field:ValidEnum(
        enumClass = WeekDay::class,
        message = "MON, TUE, WED, THU, FRI, SAT, SUN 중 하나를 입력해주세요."
    )
    @JsonProperty("lectureWeekDay")
    private var _lectureWeekDay: String?,


    @field:NotNull(message = "최대 수강 학생을 입력해주세요.")
    @JsonProperty("maxStudent")
    private var _maxStudent: Int?
) {

    val title : String
        get() = _title!!

    val registerStartAt : LocalDateTime
        get() = _registerStartAt!!.toLocalDateTime()

    val registerEndAt : LocalDateTime
        get() = _registerEndAt!!.toLocalDateTime()

    val lectureStartAt : String
        get() = _lectureStartAt!!

    val lectureEndAt : String
        get() = _lectureEndAt!!

    val lectureWeekDay : WeekDay
        get() = WeekDay.valueOf(_lectureWeekDay!!)

    val maxStudent : Int
        get() = _maxStudent!!

    private fun String.toLocalDateTime() : LocalDateTime =
        LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
}

data class LectureResponseDto(
    var title: String,
    var maxStudent: Int,
    var registerPeriod: RegisterPeriod?,
    var lectureSchedule: List<LectureSchedule>?,
    var member: Member?,
)
