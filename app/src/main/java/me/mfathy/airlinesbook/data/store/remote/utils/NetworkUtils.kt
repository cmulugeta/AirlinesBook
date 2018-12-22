package me.cmulugeta.airlinesbook.data.store.remote.utils

/**
 * Created by Mohammed Fathy on 21/12/2018.
 * dev.cmulugeta@gmail.com
 *
 * NetworkUtils is a network checker helper class for checking if the device is connected to the internet.
 */
interface NetworkUtils {
    fun hasConnection(): Boolean
}