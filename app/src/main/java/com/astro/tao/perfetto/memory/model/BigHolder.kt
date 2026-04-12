package com.astro.tao.perfetto.memory.model

class BigHolder(size: Int) {
    var data: ByteArray?

    init {
        this.data = ByteArray(size)
    }
}