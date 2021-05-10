package com.example.lesson1.networking.api

import com.example.lesson1.networking.model.Album
import com.example.lesson1.networking.model.PostModel
import com.example.lesson1.networking.model.UserNet
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("/albums")
    suspend fun getToAlbumFragment(): Response<ArrayList<Album>>

    @GET("/users")
    suspend fun getToUserFragment(): Response<ArrayList<UserNet>>

    @FormUrlEncoded
    @POST("posts")
    suspend fun sendToPostFragment(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Response<PostModel>


}