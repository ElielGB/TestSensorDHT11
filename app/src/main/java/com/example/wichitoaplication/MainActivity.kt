 package com.example.wichitoaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.AppSensor)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAcercade.setOnClickListener {
            val i : Intent = Intent(this, InfoActivity::class.java)
            startActivity(i)
        }
        btnTemperatura.setOnClickListener {
            val i : Intent = Intent(this, TemperaturaActivity::class.java)
            startActivity(i)
        }
        btnHumedad.setOnClickListener {
            val i : Intent = Intent(this, HumedadActivity::class.java)
            startActivity(i)
        }
    }
}