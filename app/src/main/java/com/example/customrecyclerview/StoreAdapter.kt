package com.example.customrecyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class StoreAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    private val items: MutableList<StoreItem>,
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = (mContext as Activity).layoutInflater.inflate(mLayoutResourceId, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        with(holder) {
            nameView.text = items[position].name
            when (items[position].logo) {
                is LogoType.LogoDrawable ->
                    logoView.setImageResource((items[position].logo as LogoType.LogoDrawable).resId)
                is LogoType.LogoURI ->
                    logoView.setImageURI((items[position].logo as LogoType.LogoURI).uri)
            }

            container.setOnClickListener {
                val logoRes = when (items[position].logo) {
                    is LogoType.LogoDrawable -> (items[position].logo as LogoType.LogoDrawable).resId.toString()
                    is LogoType.LogoURI -> (items[position].logo as LogoType.LogoURI).uri.toString()
                }

                val type = when (items[position].logo) {
                    is LogoType.LogoDrawable -> "DRAWABLE"
                    is LogoType.LogoURI -> "URI"
                }

                val intent = Intent(mContext, StoreDetailsActivity::class.java).apply {
                    putExtra("logoType", type)
                    putExtra("storeLogo", logoRes)
                    putExtra("storeName", items[position].name)
                }

                (mContext as Activity).startActivity(intent)
            }

            container.setOnLongClickListener {
                items.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, itemCount)
                true
            }
        }
    }

    inner class StoreViewHolder(viewItem: View) : RecyclerView.ViewHolder (viewItem) {
        val nameView = viewItem.findViewById<TextView>(R.id.name)!!
        val logoView = viewItem.findViewById<ImageView>(R.id.logo)!!
        val container = viewItem.findViewById<CardView>(R.id.storeItem)!!
    }
}