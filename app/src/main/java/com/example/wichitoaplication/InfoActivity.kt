package com.example.wichitoaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_humedad.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        btnRegresarMain.setOnClickListener {
            val i : Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}