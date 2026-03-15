package com.astro.tao.model


class SharedLargeData(mainSize: Int, chunkCount: Int, chunkSize: Int) {
    var bigPayload: ByteArray?
    var chunks: MutableList<ByteArray?> = ArrayList<ByteArray?>()

    init {
        bigPayload = ByteArray(mainSize)
        for (i in 0..<chunkCount) {
            chunks.add(ByteArray(chunkSize))
        }
    }
}