package com.tdc.nhom8.appdoctruyentranhonline
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tdc.nhom8.appdoctruyentranhonline.MangaDetail
import com.tdc.nhom8.appdoctruyentranhonline.Comic
import com.tdc.nhom8.appdoctruyentranhonline.R


class ComicRecyclerViewAdapter(
    private val context: Context,
    private val comicList: List<Comic>
) : RecyclerView.Adapter<ComicRecyclerViewAdapter.ComicViewHolder>() {

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comicTitle: TextView = itemView.findViewById(R.id.item_name)
        val comicAuthor: TextView = itemView.findViewById(R.id.authorName)
        val comicImage: ImageView = itemView.findViewById(R.id.item_cover_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false)
        return ComicViewHolder(view)
    }

    //gán vào comic_slider
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comicList[position]
        holder.comicTitle.text = comic.name
        holder.comicAuthor.text = comic.author.name


        val imageId = context.resources.getIdentifier(comic.coverImage, "drawable", context.packageName)
        holder.comicImage.setImageResource(imageId)

        // Sự kiện click vào item
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MangaDetail::class.java).apply {
                putExtra("comic_title", comic.name)
                putExtra("comic_description", comic.description)
                putExtra("comic_image", comic.coverImage)
                putExtra("author", comic.author)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = comicList.size
}
