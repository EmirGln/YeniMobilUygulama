package com.example.mobilproje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var btnCart: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvCategories = findViewById(R.id.rvCategories)
        btnCart = findViewById(R.id.btnCart)
        btnLogout = findViewById(R.id.btnLogout)

        // Kategori Listesi (Sadece isimler, resimler sonra eklenecek)
        val categories = listOf(
            Category("Simit ve Poğaçalar", 0),
            Category("Tatlılar", 0),
            Category("İçecekler", 0),
            Category("Börekler", 0)
        )

        // Kategori Adaptörü
        val adapter = CategoryAdapter(this, categories)
        rvCategories.layoutManager = GridLayoutManager(this, 2)
        rvCategories.adapter = adapter

        // Sepete Git Butonu
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Çıkış Yap Butonu
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
