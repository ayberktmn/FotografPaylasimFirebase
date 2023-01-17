package com.ayberk.fotografpaylasimfirebase.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.fotografpaylasimfirebase.databinding.RecyclerRowBinding
import com.ayberk.fotografpaylasimfirebase.model.Post
import com.squareup.picasso.Picasso

class HaberRecyclerAdapter(val postList: ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclerAdapter.PostHolder>(){

    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
         holder.binding.recyclerRowKullaniciEmail.text = postList[position].kullaniciEmail
         holder.binding.recyclerRowYorumText.text =postList[position].kullaniciYorum
         Picasso.get().load(postList[position].gorselUrl).into(holder.binding.recyclerRowImageview)
    }

    override fun getItemCount(): Int {
        return  postList.size
    }
}