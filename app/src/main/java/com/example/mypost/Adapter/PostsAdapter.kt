package com.example.mypost.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypost.Post
import com.example.mypost.R
import com.example.mypost.ViewPost

class PostsAdapter(var posts:List<Post>,var context:Context) :RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
      var itemView=LayoutInflater.from(parent.context).inflate(R.layout.posts_lists,parent,false)
        return PostsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        var currentPost = posts.get(position)
        holder.User.text= currentPost.userId.toString()
        holder.Id.text=currentPost.id.toString()
        holder.title.text=currentPost.title
        holder.body.text=currentPost.body
        holder.posts.setOnClickListener {
            var intent=Intent(context,ViewPost::class.java)
            intent.putExtra("Post_ID",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return posts.size
    }
}
class PostsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var User=itemView.findViewById<TextView>(R.id.tvUser)
    var Id=itemView.findViewById<TextView>(R.id.tvID)
    var title=itemView.findViewById<TextView>(R.id.tvTitle)
    var body=itemView.findViewById<TextView>(R.id.tvBody)
    var posts=itemView.findViewById<CardView>(R.id.cvPost)

}