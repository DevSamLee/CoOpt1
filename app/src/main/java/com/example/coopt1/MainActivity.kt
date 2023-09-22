package com.example.coopt1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.coopt1.ui.theme.CoOpt1Theme
import org.json.JSONArray

class MainActivity : ComponentActivity() {
    val url = "https://jsonplaceholder.typicode.com/posts"
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val btn = findViewById<Button>(R.id.btn)
            btn.setOnClickListener( View.OnClickListener {
            downloadTask()
            })
        }

        fun downloadTask(){
            val queue = Volley.newRequestQueue(this)
            val reques = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    val data = response.toString()
                    var jArray = JSONArray(data)
                    for (i in 0..jArray.length() -1) {
                        var jobject = jArray.getJSONObject(i)
                        var userid = jobject.getInt("userId")
                        var id = jobject.getInt("id")
                        var title = jobject.getString("title")
                        var body = jobject.getString("body")
                        Log.e("userId", userid.toString())
                        Log.e("id", id.toString())
                        Log.e("title", title.toString())
                        Log.e("body", body.toString())
                    }
                },
                     Response.ErrorListener {  })
            queue.add(reques)
        }
}
