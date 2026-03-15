package com.astro.tao

import com.astro.tao.model.SharedRefHolder
import com.astro.tao.model.SmallHolder
import com.astro.tao.model.TinyObject

//用静态字段把对象留住，确保你 dump 时它们还在。
object MemoryStore {
    val smallObjects: MutableList<TinyObject?> = ArrayList<TinyObject?>()
    val bigArrays: MutableList<ByteArray?> = ArrayList<ByteArray?>()
    val smallHolders: MutableList<SmallHolder?> = ArrayList<SmallHolder?>()
    val sharedRefHolders: MutableList<SharedRefHolder?> = ArrayList<SharedRefHolder?>()

    fun clearAll() {
        smallObjects.clear()
        bigArrays.clear()
        smallHolders.clear()
        sharedRefHolders.clear()
    }
}