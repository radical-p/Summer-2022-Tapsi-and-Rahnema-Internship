package com.example.fantasy.domain.usecase

import com.example.fantasy.domain.repository.*

class GetManagerFollowersUseCase(private val managerFollowersRepo: ManagerFollowersRepo) {
    suspend fun execute(): ManagerFollowers {
        return managerFollowersRepo.getManagerFollowers()
    }
}

class GetManagerFollowingsUseCase(private val managerFollowingsRepo: ManagerFollowingsRepo) {
    suspend fun execute(): ManagerFollowings {
        return managerFollowingsRepo.getManagerFollowings()
    }
}

class GetManagerInfoUseCase(private val managerInfoRepo: ManagerInfoRepo) {
    suspend fun execute(managerId: String): ManagerInfo {
        return managerInfoRepo.getManagerInfo(managerId = managerId)
    }
}

class GetManagerFeedUseCase(private val managerFeedRepo: ManagerFeedRepo) {
    suspend fun execute(): ManagerFeed {
        return managerFeedRepo.getManagerFeed()
    }
}

class SearchManagerUseCase(private val searchManagerRepo: SearchManagerRepo) {
    suspend fun execute(searchInput: String): ManagerFollowers {
        return searchManagerRepo.searchManager(searchInput = searchInput)
    }
}

class FollowManagerUseCase(private val followManagerRepo: FollowManagerRepo) {
    suspend fun execute(managerId: String): Unit {
        return followManagerRepo.followManager(managerId = managerId)
    }
}

class SearchManagerFollowersUseCase(private val searchManagerFollowersRepo: SearchManagerFollowersRepo) {
    suspend fun execute(searchInput: String): ManagerFollowers {
        return searchManagerFollowersRepo.searchManagerFollowers(searchInput = searchInput)
    }
}

class SearchManagerFollowingsUseCase(private val searchManagerFollowingsRepo: SearchManagerFollowingsRepo) {
    suspend fun execute(searchInput: String): ManagerFollowings {
        return searchManagerFollowingsRepo.searchManagerFollowings(searchInput = searchInput)
    }
}