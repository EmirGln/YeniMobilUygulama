package com.example.mobilproje

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val etCardNumber = findViewById<EditText>(R.id.etCardNumber)
        val etCardHolder = findViewById<EditText>(R.id.etCardHolder)
        val etExpiryDate = findViewById<EditText>(R.id.etExpiryDate)
        val etCVV = findViewById<EditText>(R.id.etCVV)
        val btnPay = findViewById<Button>(R.id.btnPay)

        btnPay.setOnClickListener {
            val cardNumber = etCardNumber.text.toString().trim()
            val cardHolder = etCardHolder.text.toString().trim()
            val expiryDate = etExpiryDate.text.toString().trim()
            val cvv = etCVV.text.toString().trim()

            if (cardNumber.isEmpty() || cardHolder.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ödeme Başarılı! Siparişiniz alındı.", Toast.LENGTH_LONG).show()


                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}
