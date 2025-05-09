package com.example.mobilproje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductListActivity : AppCompatActivity() {

    private lateinit var gvProducts: GridView
    private lateinit var tvCategoryTitle: TextView
    private lateinit var btnBackToCategories: Button
    private lateinit var btnGoToCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        gvProducts = findViewById(R.id.gvProducts)
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle)
        btnBackToCategories = findViewById(R.id.btnBackToCategories)
        btnGoToCart = findViewById(R.id.btnGoToCart)

        // Kategori adı alınıyor
        val categoryName = intent.getStringExtra("categoryName")
        tvCategoryTitle.text = categoryName

        // Ürün listesi
        val productList = when (categoryName) {
            "Simit ve Poğaçalar" -> listOf(
                Product("Simit", 5.0),
                Product("Açma", 6.0),
                Product("Poğaça", 10.0)
            )
            "Tatlılar" -> listOf(
                Product("Ekler", 13.0),
                Product("Tiramisu", 18.0),
                Product("Mozaik Pasta", 20.0),
                Product("Cheesecake", 30.0)
            )
            "İçecekler" -> listOf(
                Product("Cola", 15.0),
                Product("Çay", 10.0),
                Product("Kahve", 30.0),
                Product("Ev Yapımı Limonata", 20.0)
            )
            "Börekler" -> listOf(
                Product("Su Böreği", 12.5),
                Product("Peynirli Börek", 22.0),
                Product("Kıymalı Börek", 20.0)
            )
            else -> emptyList()
        }

        // Ürün adaptörü
        val adapter = ProductAdapter(this, productList)
        gvProducts.adapter = adapter

        // Geri butonuna tıklama
        btnBackToCategories.setOnClickListener {
            finish()
        }

        // Sepete git butonuna tıklama
        btnGoToCart.setOnClickListener {
            if (CartManager.getCart().isNotEmpty()) {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Sepetiniz boş!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
