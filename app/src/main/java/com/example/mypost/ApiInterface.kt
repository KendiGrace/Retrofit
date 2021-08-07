package com.example.mypost

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPosts():Call<List<Post>>

    @GET("posts/{id}")
    fun getPostId(@Path(value ="id") postId:Int):Call<Post>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId:Int):Call<List<Comments>>
}