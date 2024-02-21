package com.example.secondtryrestapidata

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondtryrestapidata.databinding.ActivityMainBinding
import com.example.secondtryrestapidata.ui.theme.SecondTryRestAPIDataTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var data = ArrayList<Products>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewAPI.layoutManager = LinearLayoutManager(this)

        // Use the MainScope to launch the coroutine
        MainScope().launch {
            getAllProducts()
        }
    }

    private suspend fun getAllProducts() {
        try {
            val retrofit = withContext(Dispatchers.IO) {
                ServiceBuilder.buildService(ServiceInterface::class.java)
            }

            // Use suspendCoroutine to convert callback-based API to coroutine
            val response = suspendCoroutine<Response<ApiResponse>> { continuation ->
                retrofit.getAllProducts().enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                        continuation.resume(response)
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })
            }

            // Directly update UI without checking response
            withContext(Dispatchers.Main) {
                val responseBody = response.body()
                data = responseBody?.results ?: arrayListOf()
                val adapter = ProductAdapter(data)
                binding.recyclerViewAPI.adapter = adapter
            }

        } catch (ex: Exception) {
            Log.e("Exception", "Error during API call: ${ex.message}")
        }
    }

}