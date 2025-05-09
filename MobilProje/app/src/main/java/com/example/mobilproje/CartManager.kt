package com.example.mobilproje

object CartManager {
    private val cartList = mutableListOf<Product>()

    fun addToCart(product: Product) {
        val existing = cartList.find { it.name == product.name }
        if (existing != null) {
            existing.quantity += product.quantity
        } else {
            cartList.add(product.copy())
        }
    }

    fun removeFromCart(product: Product) {
        cartList.remove(product)
    }

    fun clearCart() {
        cartList.clear()
    }

    fun getCart(): MutableList<Product> {
        return cartList
    }

    fun getTotalItemCount(): Int {
        return cartList.sumOf { it.quantity }
    }

    fun getTotalPrice(): Double {
        return cartList.sumOf { it.totalPrice() }
    }
}
