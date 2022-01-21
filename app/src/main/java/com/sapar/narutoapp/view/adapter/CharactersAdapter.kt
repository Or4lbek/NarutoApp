package com.sapar.narutoapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.sapar.narutoapp.R
import com.sapar.narutoapp.model.CharactersItem
import com.squareup.picasso.Picasso

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
//        holder.clan.text = (characters[position].clan)

        Picasso.with(mContext)
            .load(characters[position].image_url)
            .placeholder(R.drawable.back)
            .fit()
            .into(holder.image)
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(mContext,
            R.anim.recycler_view_animation
        ))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class MyViewHolder(itemView: View, noteListener: OnItemNoteListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView
//        var clan: TextView
        var image: ImageView

        var noteListener: OnItemNoteListener
        override fun onClick(v: View) {
            noteListener.onNoteClick(getAdapterPosition())
        }

        init {
            name = itemView.findViewById<TextView>(R.id.textViewTitle)
//            clan = itemView.findViewById<TextView>(R.id.textViewClan)
            image = itemView.findViewById(R.id.imageViewUser)
            this.noteListener = noteListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnItemNoteListener {
        fun onNoteClick(position: Int)
    }
}