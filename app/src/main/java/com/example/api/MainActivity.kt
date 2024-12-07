package com.example.api


import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mtapi3.ProductResponse
import com.example.mtapi3.ProductService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    obtenerProductos()
}
private fun obtenerProductos() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.jsonkeeper.com/") // Updated base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ProductService::class.java)
    val call = service.getProducts()


    call.enqueue(object : Callback<ProductResponse> {
        override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
            if (response.isSuccessful) {
                val products = response.body()?.products
                products?.forEach { product ->
                    listadoTexto.T
                    Log.d("MainActivity","Product: ${product.name}")
                }
            }else {
                Log.e("MainActivity","Product: ${product.name}")

            }
        }

        override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
            Log.e("MainActivity", "Error: ${t.message}")
        }
    })
}