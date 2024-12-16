package com.tdc.nhom8.appdoctruyentranhonline

import com.tdc.nhom8.appdoctruyentranhonline.Author
import com.tdc.nhom8.appdoctruyentranhonline.Comic
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tdc.nhom8.appdoctruyentranhonline.databinding.ItemSliderBinding


class SliderAdapter(
    private val itemList: List<Comic>,
    private val onItemClick: (Comic) -> Unit
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = itemList[position]
        holder.iName.text = item.name
        holder.iAuthorName.text = item.author.name

        // Set image for comic cover
        val imageResId = getDrawableId(holder.itemView.context, item.coverImage)
        holder.iCoverImage.setImageResource(imageResId.takeIf { it != 0 } ?: R.drawable.default_image)

        // Set on click listener for item
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iCoverImage: ImageView = itemView.findViewById(R.id.item_cover_image)
        val iName: TextView = itemView.findViewById(R.id.item_name)
        val iAuthorName: TextView = itemView.findViewById(R.id.authorName)
    }

    // Helper function to get drawable resource ID
    private fun getDrawableId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}
