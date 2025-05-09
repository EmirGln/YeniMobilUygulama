package com.example.mobilproje

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CartActivity : AppCompatActivity() {

    private lateinit var layoutCartItems: LinearLayout
    private lateinit var btnConfirmOrder: Button
    private lateinit var tvOrderStatus: TextView
    private lateinit var btnCustomBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sepet"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        layoutCartItems = findViewById(R.id.layoutCartItems)
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder)
        tvOrderStatus = findViewById(R.id.tvOrderStatus)
        btnCustomBack = findViewById(R.id.btnCustomBack)

        // Geri butonu
        btnCustomBack.setOnClickListener {
            finish()
        }

        // Sepeti güncelle
        updateCartUI()

        // Siparişi onayla
        btnConfirmOrder.setOnClickListener {
            if (CartManager.getCart().isNotEmpty()) {
                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
            } else {
                tvOrderStatus.text = "\u274C Sepetiniz boş!"
            }
        }
    }

    private fun updateCartUI() {
        layoutCartItems.removeAllViews()

        val cartItems = CartManager.getCart()

        for ((index, item) in cartItems.withIndex()) {
            val itemLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(8, 8, 8, 8)
            }

            val tvInfo = TextView(this).apply {
                text = "${item.quantity} x ${item.name} = ₺${item.totalPrice()}"
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val btnRemove = Button(this).apply {
                text = "Sil"
                setOnClickListener {
                    // **Ürünü CartManager'dan sil**
                    CartManager.removeFromCart(item)
                    updateCartUI()
                }
            }

            itemLayout.addView(tvInfo)
            itemLayout.addView(btnRemove)

            layoutCartItems.addView(itemLayout)
        }

        // Sepet boş mu kontrolü
        if (cartItems.isEmpty()) {
            tvOrderStatus.text = "\u274C Sepetiniz boş!"
            btnConfirmOrder.isEnabled = false
        } else {
            tvOrderStatus.text = ""
            btnConfirmOrder.isEnabled = true
        }
    }
}
