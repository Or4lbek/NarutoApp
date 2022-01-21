package com.sapar.narutoapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.sapar.narutoapp.R
import com.sapar.narutoapp.model.CharactersItem
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class CharactersAdapter(
    private val characters: List<CharactersItem>,
    private val mContext: Context,
    private val mOnItemNoteListener: OnItemNoteListener
) : RecyclerView.Adapter<CharactersAdapter.MyViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
        view = layoutInflater.inflate(R.layout.character_record_item, parent, false)
        return MyViewHolder(view, mOnItemNoteListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = (characters[position].name)

        Picasso.with(mContext)
            .load(characters[position].imageUrl)
            .placeholder(R.drawable.back)
            .fit()
            .into(holder.image)

        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                mContext,
                R.anim.recycler_view_animation
            )
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class MyViewHolder(itemView: View, private var noteListener: OnItemNoteListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView = itemView.findViewById<TextView>(R.id.textViewTitle)

        var image: ImageView = itemView.findViewById(R.id.imageViewUser)

        override fun onClick(v: View) {
            noteListener.onNoteClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemNoteListener {
        fun onNoteClick(position: Int)
    }
}