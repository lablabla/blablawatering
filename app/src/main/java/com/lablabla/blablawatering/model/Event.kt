package com.lablabla.blablawatering.model

import com.lablabla.blablawatering.model.enums.WateringState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Event(
    val wateringState: WateringState = WateringState.Off,
    val dateAndTime: LocalDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now())
)