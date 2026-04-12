package com.astro.tao.testupdateui

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.TextView

class FloatWindowThread() : Thread("FloatWindowThread") {
    private lateinit var handler: Handler
    private lateinit  var mContext: Context

    private lateinit var mTextView : TextView

    constructor(context: Context) : this() {
        mContext = context
        mTextView = TextView(mContext).apply {
            text = "Created on: ${Thread.currentThread().name}"
            setBackgroundColor(0x88FF0000.toInt())
            setTextColor(Color.BLACK)
            setPadding(24, 24, 24, 24)
        }
    }

    fun getTextView() : TextView {
        return mTextView
    }

    override fun run() {
        Looper.prepare()
        Log.d("zhifei", "FloatWindowThread start");
        handler = Handler(Looper.myLooper()!!)

        val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager


        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            // 仅示例：不同版本/权限要求不同
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        wm.addView(mTextView, params)

        // 在创建该 View 的同一个子线程里更新它
        handler.postDelayed(object : Runnable {
            override fun run() {
                mTextView.text = "Updated on: ${Thread.currentThread().name}"
                handler.postDelayed(this, 1000)
            }
        }, 1000)

        Looper.loop()
    }
}

// 使用方式：
// FloatWindowThread(applicationContext).start()