package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class ManagerProfileDataDto(
    @SerializedName("data")
    val data: ManagerProfileManagerDto,
)

data class ManagerProfileManagerDto(
    @SerializedName("manager")
    val  manager: ManagerProfileDataFields
)

data class ManagerProfileDataFields(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("img")
    val image: ManagerImage,
)

data class ManagerImage(
    @SerializedName("url")
    val imageUrl : String?
)