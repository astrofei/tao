package com.astro.tao.openGLES2

import android.app.ActivityManager
import android.content.pm.ConfigurationInfo
import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OpenGlESActivity : AppCompatActivity() {
    private val CONTEXT_CLIENT_VERSION = 3
    private lateinit var mGLSurfaceView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGLSurfaceView = GLSurfaceView(this)

        if (detectOpenGLES30()) {
            // 告诉表面视图我们要创建一个与 OpenGL ES 3.0 兼容的上下文，
            // 并设置一个与 OpenGL ES 3.0 兼容的渲染器。
            mGLSurfaceView.setEGLContextClientVersion(CONTEXT_CLIENT_VERSION)
            mGLSurfaceView.setRenderer(HelloTriangleRenderer(this))
            setContentView(mGLSurfaceView)
        }
    }

    private fun detectOpenGLES30() : Boolean {
        val am : ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val info : ConfigurationInfo = am.deviceConfigurationInfo
        return (info.reqGlEsVersion >= 0x30000)
    }

    override fun onResume() {
        super.onResume()
        mGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mGLSurfaceView.onPause()
    }
}