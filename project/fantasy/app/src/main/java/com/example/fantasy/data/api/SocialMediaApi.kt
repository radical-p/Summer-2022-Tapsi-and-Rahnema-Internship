package com.example.fantasy.data.api

import com.example.fantasy.data.models.ManagerFeedDto
import com.example.fantasy.data.models.ManagerFollowersDto
import com.example.fantasy.data.models.ManagerFollowingsDto
import com.example.fantasy.data.models.ManagerInfoDto
import com.example.fantasy.domain.repository.FollowManagerRequest
import com.example.fantasy.domain.repository.ManagerSearchInput
import retrofit2.http.*

interface ManagerFollowersApi {
    @GET("connections/followers")
    suspend fun getManagerFollowers(@Header("token") token: String): ManagerFollowersDto
}

interface ManagerFollowingsApi {
    @GET("connections/followings")
    suspend fun getManagerFollowings(@Header("token") token: String): ManagerFollowingsDto
}

interface ManagerInfoApi {
    @GET("managers")
    suspend fun getManagerInfo(@Query("managerId") managerId: String): ManagerInfoDto
}

interface ManagerFeedApi {
    @GET("feeds")
    suspend fun getManagerFeed(@Header("token") token: String): ManagerFeedDto
}

interface SearchManagerApi {
    @POST("users/search")
    suspend fun searchManager(@Header("token") token: String, @Body name: ManagerSearchInput): ManagerFollowersDto
}

interface FollowManagerApi {
    @POST("connections/follow")
    suspend fun followManager(@Header("token") token: String, @Body target: FollowManagerRequest): Unit
}

interface SearchManagerFollowersApi {
    @POST("connections/followers/search")
    suspend fun searchManagerFollowers(@Header("token") token: String, @Body name: ManagerSearchInput): ManagerFollowersDto
}

interface SearchManagerFollowingsApi {
    @POST("connections/followings/search")
    suspend fun searchManagerFollowings(@Header("token") token: String, @Body name: ManagerSearchInput): ManagerFollowingsDto
}