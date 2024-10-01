package com.example.colorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import com.example.colorapp.network.RetrofitClient
import com.example.colorapp.network.ColorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPantoneCode = findViewById<EditText>(R.id.etPantoneCode)
        val btnShowColor = findViewById<Button>(R.id.btnShowColor)
        val tvColorName = findViewById<TextView>(R.id.tvColorName)
        val colorView = findViewById<View>(R.id.colorView)

        btnShowColor.setOnClickListener {
            val hexCode = etPantoneCode.text.toString().trim().replace("#", "")

            if (hexCode.isNotEmpty()) {
                // Llamada a la API
                RetrofitClient.instance.getColorByHex(hexCode)
                    .enqueue(object : Callback<ColorResponse> {
                        override fun onResponse(call: Call<ColorResponse>, response: Response<ColorResponse>) {
                            val colorResponse = response.body()
                            if (colorResponse != null) {
                                tvColorName.text = colorResponse.name.value
                                colorView.setBackgroundColor(android.graphics.Color.parseColor(colorResponse.hex.value))
                            }
                        }

                        override fun onFailure(call: Call<ColorResponse>, t: Throwable) {
                            tvColorName.text = "Error al obtener el color"
                        }
                    })
            } else {
                tvColorName.text = "Introduce un código válido"
            }
        }
    }
}
