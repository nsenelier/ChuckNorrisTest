package com.example.chucknorristest.adapter

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorristest.model.Category
import com.example.chucknorristest.model.Joke

class CategoryAdapter: ListAdapter<Category, RecyclerView.ViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val category = getItem(position)
        (holder as CategoryViewHolder).bind(category)
    }

    class CategoryViewHolder(
        composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView) {
        fun bind(category: Category) {
            (itemView as ComposeView).setContent {
                MdcTheme {
                    CategoryListItemView(category = category) {
                        navigateToCategory(category)
                    }
                }
            }
        }

        private fun navigateToCategory(category: Category) {

            itemView.findNavController().navigate()
        }
    }
}

private class CategoryDiffCallback: DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
      return oldItem.categoryId == newItem.categoryId
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}