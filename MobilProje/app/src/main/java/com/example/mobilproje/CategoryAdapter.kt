package com.example.mobilproje

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val context: Context,
    private val categoryList: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.tvCategoryName.text = category.name

        // Kategoriye tıklandığında ilgili ürün sayfasına git
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductListActivity::class.java)
            intent.putExtra("categoryName", category.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }
}
