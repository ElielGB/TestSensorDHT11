package com.example.wichitoaplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_humedad.*

class TemperaturaActivity : AppCompatActivity() {
    private lateinit var databaseFirebase: FirebaseDatabase
    private lateinit var referencia: DatabaseReference
    private var dataset: ArrayList<Entry> = ArrayList()

    private var bandera = true
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        databaseFirebase = FirebaseDatabase.getInstance()
        referencia = databaseFirebase.getReference("SensorDHT11")
        obtenerDatos()

        btnRegresarMain.setOnClickListener {
            val i: Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }


    private fun obtenerDatos() {
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var lista = ArrayList<Float>()
                var tamanoLista:Int = snapshot.childrenCount.toInt()
                for (data in snapshot.children) {
                    val dato = data.child("Temperatura").value.toString()

                    println("------------------ $dato")
                    var entry = Entry(i.toFloat(), dato.toFloat())
                    if (bandera) {
                        dataset.add(entry)
                        i++
                    }else if(i == tamanoLista) {
                        dataset.add(entry)
                        i++
                    }
                    lista.add(dato.toFloat())
                }

                if (lista.size > 0) {
                    obtenerGrafico()
                    bandera=false
                }

            }
        })
    }


    private fun obtenerGrafico() {
        var lineDataSet: LineDataSet = LineDataSet(dataset,"Temperatura")
        var iLineDataSets:ArrayList<ILineDataSet> = ArrayList()
        iLineDataSets.add(lineDataSet)

        var lineData: LineData = LineData(iLineDataSets)
        linechart.data = lineData
        linechart.invalidate()
        linechart.setNoDataText("Sin datos")

        lineDataSet.color = Color.BLACK
        lineDataSet.setCircleColor(Color.BLUE)
        lineDataSet.setDrawCircles(true)
        lineDataSet.lineWidth = 1f
        lineDataSet.circleRadius = 3f
        lineDataSet.valueTextSize = 8f
        lineDataSet.valueTextColor = Color.BLACK
    }
}