package com.astro.tao.model

class BigHolder(size: Int) {
    var data: ByteArray?

    init {
        this.data = ByteArray(size)
    }
}