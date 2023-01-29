package com.akinguldere.yktcase.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akinguldere.yktcase.databinding.ItemProductBinding
import com.akinguldere.yktcase.model.FSProduct

class ProductsAdapter :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<FSProduct>() {
        override fun areItemsTheSame(
            oldItem: FSProduct,
            newItem: FSProduct
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FSProduct,
            newItem: FSProduct
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val binding = ItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val movie = differ.currentList[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ProductsViewHolder(
        private val binding: ItemProductBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fsProduct: FSProduct) {

            binding.apply {
                product = fsProduct
                executePendingBindings()

                mcv.setOnClickListener {
                    onItemClickListener?.let {
                        it(fsProduct)
                    }
                }

            }
        }
    }

    private var onItemClickListener: ((FSProduct) -> Unit)? = null

    fun setOnItemClickListener(listener: (FSProduct) -> Unit) {
        onItemClickListener = listener
    }

}