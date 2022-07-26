package com.lablabla.blablawatering.model.enums

enum class SolenoidState {
    Open,
    Closed;

    companion object {
        private val VALUES = values()
        fun getByValue(value: Int) = VALUES.firstOrNull { it.ordinal == value }
    }
}