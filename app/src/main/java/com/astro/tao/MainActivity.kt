package com.astro.tao


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.astro.tao.perfetto.memory.LeakHolder
import com.astro.tao.perfetto.memory.MemoryStore
import com.astro.tao.perfetto.memory.MemoryStoreOther
import com.astro.tao.perfetto.memory.PerfettoMemoryActivity
import com.astro.tao.perfetto.memory.utils.MemoryBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val perfettoMemory = findViewById<Button?>(R.id.perfettomemory)

        perfettoMemory.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this, PerfettoMemoryActivity::class.java))
        })

    }
}