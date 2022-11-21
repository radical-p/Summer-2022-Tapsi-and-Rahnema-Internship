package com.example.fantasy.domain.repository

interface ManagerFollowersRepo {
    suspend fun getManagerFollowers(): ManagerFollowers
}

interface ManagerFollowingsRepo {
    suspend fun getManagerFollowings(): ManagerFollowings
}

interface ManagerInfoRepo {
    suspend fun getManagerInfo(managerId: String): ManagerInfo
}

interface ManagerFeedRepo {
    suspend fun getManagerFeed(): ManagerFeed
}

interface SearchManagerRepo {
    suspend fun searchManager(searchInput: String): ManagerFollowers
}

interface FollowManagerRepo {
    suspend fun followManager(managerId: String): Unit
}

interface SearchManagerFollowersRepo {
    suspend fun searchManagerFollowers(searchInput: String): ManagerFollowers
}

interface SearchManagerFollowingsRepo {
    suspend fun searchManagerFollowings(searchInput: String): ManagerFollowings
}