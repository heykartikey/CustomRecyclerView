package com.example.customrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val storeItems = generateItems()
    private lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeAdapter = StoreAdapter(this, R.layout.item_store, storeItems)

        findViewById<RecyclerView>(R.id.storeListView).apply {
            adapter = storeAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it == null) {
                Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
            } else {
                storeItems.add(StoreItem(it.pathSegments.last(), LogoType.LogoURI(it)))
                storeAdapter.notifyItemInserted(storeItems.size)
            }
        }

        findViewById<FloatingActionButton>(R.id.fBtnAddItem).setOnClickListener {
            imagePicker.launch("image/*")
        }
    }

    private fun generateItems(): MutableList<StoreItem> {
        return mutableListOf(
            StoreItem("Copa Club", LogoType.LogoDrawable(R.drawable.bar)),
            StoreItem("Boba Tea", LogoType.LogoDrawable(R.drawable.drink)),
            StoreItem("Coffee Shop", LogoType.LogoDrawable(R.drawable.coffee_shop)),
            StoreItem("Montgomery", LogoType.LogoDrawable(R.drawable.fashion)),
            StoreItem("Moka", LogoType.LogoDrawable(R.drawable.moka)),
            StoreItem("Pizza", LogoType.LogoDrawable(R.drawable.pizza)),
            StoreItem("Prosciutto", LogoType.LogoDrawable(R.drawable.prosciutto)),
            StoreItem("Happy Bowl", LogoType.LogoDrawable(R.drawable.restaurant)),
        )
    }
}