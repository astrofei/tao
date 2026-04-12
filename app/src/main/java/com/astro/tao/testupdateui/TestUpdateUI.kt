package com.astro.tao.testupdateui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.astro.tao.R
class TestUpdateUI: AppCompatActivity() {
    lateinit var floatTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_update_ui)
        findViewById<Button>(R.id.updateui1).setOnClickListener ( View.OnClickListener { v: View? ->
            startActivity(Intent(this, OnCreateUpdateUI::class.java))
        } )

        findViewById<Button>(R.id.updateui2).setOnClickListener ( View.OnClickListener {v : View? ->
            val floatWindowThread = FloatWindowThread(applicationContext)
            floatWindowThread.start()
            floatTextView = floatWindowThread.getTextView()
        } )

        findViewById<Button>(R.id.updateui3).setOnClickListener (View.OnClickListener {v : View? ->
            floatTextView.setText("update ui form main UI thread")
        })

         val overlayLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return@registerForActivityResult
                if (Settings.canDrawOverlays(this)) {
                    Log.d("zhifei", "grant");
                } else {
                    // 未授权
                    Log.d("zhifei", "not grant");
                }
            }


            overlayLauncher.launch(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
            )
    }
}