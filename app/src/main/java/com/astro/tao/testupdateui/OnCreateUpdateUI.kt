package com.astro.tao.testupdateui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.astro.tao.R

class OnCreateUpdateUI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.on_create_update_ui)

        val tv = findViewById<TextView>(R.id.updateText)
        Thread {
            // onCreate 阶段，ViewRootImpl 可能尚未建立
            // 因此这里有机会“侥幸”不抛 CalledFromWrongThreadException
            tv.text = "Updated from background thread"
        }.start()
    }
}