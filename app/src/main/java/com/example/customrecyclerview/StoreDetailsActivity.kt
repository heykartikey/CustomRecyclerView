package com.example.customrecyclerview

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class StoreDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_details)

        val storeLogo = intent.extras?.getString("storeLogo")!!
        val storeName = intent.extras?.getString("storeName")!!
        val logoType = intent.extras?.getString("logoType")!!

        val textView = findViewById<TextView>(R.id.storeName)
        val logoView = findViewById<ImageView>(R.id.storeLogo)

        textView.text = storeName

        when (logoType) {
            "DRAWABLE" -> logoView.setImageResource(storeLogo.toInt())
            "URI" -> logoView.setImageURI(Uri.parse(storeLogo))
        }
    }
}