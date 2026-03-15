package com.astro.tao.model

import android.util.Log

class TinyObject(var id: Int) {
    var timestamp: Long
    var name: String?

    init {
        this.timestamp = System.nanoTime()
        this.name = "Tiny-" + id
    }
}