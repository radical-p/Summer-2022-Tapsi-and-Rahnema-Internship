package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.*
import com.example.fantasy.data.models.toManagerFeed
import com.example.fantasy.data.models.toManagerFollowers
import com.example.fantasy.data.models.toManagerFollowings
import com.example.fantasy.data.models.toManagerInfo
import com.example.fantasy.domain.repository.*

class ManagerFollowersRepoImpl(
    private val managerFollowersApi: ManagerFollowersApi,
    private val dataStore: Storage,
): ManagerFollowersRepo {
    override suspend fun getManagerFollowers(): ManagerFollowers {
        val token = dataStore.get("token")
        return managerFollowersApi.getManagerFollowers(token!!).toManagerFollowers()
    }
}

class ManagerFollowingsRepoImpl(
    private val managerFollowingsApi: ManagerFollowingsApi,
    private val dataStore: Storage,
): ManagerFollowingsRepo {
    override suspend fun getManagerFollowings(): ManagerFollowings {
        val token = dataStore.get("token")
        return managerFollowingsApi.getManagerFollowings(token!!).toManagerFollowings()
    }
}

class ManagerInfoRepoImpl(
    private val managerInfoApi: ManagerInfoApi,
): ManagerInfoRepo {
    override suspend fun getManagerInfo(managerId: String): ManagerInfo {
        return managerInfoApi.getManagerInfo(managerId).toManagerInfo()
    }
}

class ManagerFeedRepoImpl(
    private val managerFeedApi: ManagerFeedApi,
    private val dataStore: Storage,
): ManagerFeedRepo {
    override suspend fun getManagerFeed(): ManagerFeed {
        val token = dataStore.get("token")
        return managerFeedApi.getManagerFeed(token!!).toManagerFeed()
    }
}

class SearchManagerRepoImpl(
    private val searchManagerApi: SearchManagerApi,
    private val dataStore: Storage,
): SearchManagerRepo {
    override suspend fun searchManager(searchInput: String): ManagerFollowers {
        val token = dataStore.get("token")
        return searchManagerApi.searchManager(token!!, ManagerSearchInput(searchInput)).toManagerFollowers()
    }
}

class FollowManagerRepoImpl(
    private val followManagerApi: FollowManagerApi,
    private val dataStore: Storage,
): FollowManagerRepo {
    override suspend fun followManager(managerId: String) {
        val token = dataStore.get("token")
        return followManagerApi.followManager(token!!, FollowManagerRequest(managerId))
    }
}

class SearchManagerFollowersRepoImpl(
    private val searchManagerFollowersApi: SearchManagerFollowersApi,
    private val dataStore: Storage,
): SearchManagerFollowersRepo {
    override suspend fun searchManagerFollowers(searchInput: String): ManagerFollowers {
        val token = dataStore.get("token")
        return searchManagerFollowersApi.searchManagerFollowers(token!!, ManagerSearchInput(searchInput)).toManagerFollowers()
    }
}

class SearchManagerFollowingsRepoImpl(
    private val searchManagerFollowingsApi: SearchManagerFollowingsApi,
    private val dataStore: Storage,
): SearchManagerFollowingsRepo {
    override suspend fun searchManagerFollowings(searchInput: String): ManagerFollowings {
        val token = dataStore.get("token")
        return searchManagerFollowingsApi.searchManagerFollowings(token!!, ManagerSearchInput(searchInput)).toManagerFollowings()
    }
}