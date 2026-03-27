package com.astro.tao.perfetto.memory

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.astro.tao.R
import com.astro.tao.perfetto.memory.utils.MemoryBuilder

class PerfettoMemoryActivity: AppCompatActivity() {
    private var statusText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfetto_memory)

        statusText = findViewById<TextView?>(R.id.statusText)

        val btnSmall = findViewById<Button?>(R.id.btnSmallObjects)
        val btnBig = findViewById<Button?>(R.id.btnBigArrays)
        val btnHolderTree = findViewById<Button?>(R.id.btnHolderTree)
        val btnShared = findViewById<Button?>(R.id.btnSharedGraph)
        val btnLeak = findViewById<Button?>(R.id.btnLeakActivity)
        val btnClear = findViewById<Button?>(R.id.btnClearAll)

        btnSmall.setOnClickListener(View.OnClickListener { v: View? ->
            MemoryBuilder.createManySmallObjects()
            updateStatus("Created many small objects")
        })

        btnBig.setOnClickListener(View.OnClickListener { v: View? ->
            MemoryBuilder.createBigArrays()
            updateStatus("Created big byte arrays")
        })

        btnHolderTree.setOnClickListener(View.OnClickListener { v: View? ->
            MemoryBuilder.createHolderTree()
            updateStatus("Created small holder with huge dominated graph")
        })

        btnShared.setOnClickListener(View.OnClickListener { v: View? ->
            MemoryBuilder.createSharedGraph()
            updateStatus("Created shared graph")
        })

        btnLeak.setOnClickListener(View.OnClickListener { v: View? ->
            LeakHolder.leakActivity(this)
            updateStatus("Leaked current Activity into static holder")
        })

        btnClear.setOnClickListener(View.OnClickListener { v: View? ->
            MemoryStore.clearAll()
            MemoryStoreOther.clearAll()
            LeakHolder.clear()
            System.gc()
            updateStatus("Cleared all strong references")
        })

        updateStatus("Ready")
    }

    private fun updateStatus(msg: String?) {
        val text = """
            ${msg}
            Store status:
            smallObjects =   ${MemoryStore.smallObjects.size}
            bigArrays =   ${MemoryStore.bigArrays.size}
            smallHolders =   ${MemoryStore.smallHolders.size}
            sharedHolders =   ${MemoryStore.sharedRefHolders.size}
            leakedActivity =   ${(LeakHolder.activity != null)}
        """.trimIndent()
        statusText!!.setText(text)
    }
}
