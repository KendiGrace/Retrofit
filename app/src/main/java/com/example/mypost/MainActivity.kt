package com.example.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypost.Adapter.PostsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    lateinit var rvPosts:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPosts=findViewById(R.id.rvPosts)
        rvPosts.layoutManager=LinearLayoutManager(baseContext)
        getPosts()
    }
    fun getPosts(){
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.getPosts()
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
             if (response.isSuccessful){
                 var postList=response.body()!!
                 if(postList!=null){
                     var postsAdapter=PostsAdapter(postList,baseContext)
                     rvPosts.adapter=postsAdapter
                 }

                 Toast.makeText(baseContext,postList.size.toString(),Toast.LENGTH_LONG).show()
             }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}