package me.cmulugeta.airlinesbook.domain.model

/**
 * Created by Mohammed Fathy.
 * dev.cmulugeta@gmail.com
 */
data class Schedule(val duration: String = "",
                    val flights: List<Flight>?)
