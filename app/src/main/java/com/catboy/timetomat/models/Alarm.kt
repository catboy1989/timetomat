package com.catboy.timetomat.models

enum class Alarm {
    SHORT, LONG, WORK;

    override fun toString(): String {
        return when(this) {
            SHORT -> "short"
            LONG -> "long"
            WORK -> "work"
        }
    }
}