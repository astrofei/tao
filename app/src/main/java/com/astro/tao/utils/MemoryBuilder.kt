package com.astro.tao.utils


import android.util.Log
import androidx.annotation.Size
import com.astro.tao.MemoryStore
import com.astro.tao.MemoryStoreOther
import com.astro.tao.model.SharedLargeData
import com.astro.tao.model.SharedRefHolder
import com.astro.tao.model.SmallHolder
import com.astro.tao.model.TinyObject
import com.astro.tao.model.TreeNode

object MemoryBuilder {
    fun createManySmallObjects() {
        for (i in 0..30000 - 1) {
            val tinyObject = TinyObject(i)
            MemoryStore.smallObjects.add(tinyObject)
            MemoryStoreOther.smallObjects.add(tinyObject)
        }
    }

    fun createBigArrays() {
        for (i in 0..19) {
            // 每个 2MB，总共约 40MB
            MemoryStore.bigArrays.add(ByteArray(2 * 1024 * 1024))
        }
    }

    fun createHolderTree() {
        val root = TreeNode("root", 512 * 1024)

        for (i in 0..9) {
            val child = TreeNode("child-" + i, 512 * 1024)
            for (j in 0..9) {
                val grandChild = TreeNode("grand-" + i + "-" + j, 256 * 1024)
                child.addChild(grandChild)
            }
            root.addChild(child)
        }

        val holder = SmallHolder("holder-" + System.nanoTime(), root)
        MemoryStore.smallHolders.add(holder)
    }

    fun createSharedGraph() {
        val shared = SharedLargeData(
            4 * 1024 * 1024,
            10,
            512 * 1024
        )

        val a = SharedRefHolder("A", shared)
        val b = SharedRefHolder("B", shared)

        MemoryStore.sharedRefHolders.add(a)
        MemoryStore.sharedRefHolders.add(b)
    }
}