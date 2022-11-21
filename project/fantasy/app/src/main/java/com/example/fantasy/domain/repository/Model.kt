package com.example.fantasy.domain.repository

import android.media.Image

data class ManagerSearchInput(
    val name: String,
)

data class ManagerProfileRequest(
    val first_name: String,
    val last_name: String,
    val email: String,
    val country: String,
    val username: String,
    val password: String,
    val image: String?,
)

data class FollowManagerRequest(
    val target: String
)

data class Player(
    val price: String,
    val rating: String,
    val name: String,
    val team: String,
    val id: String,
    val isInTeam: Boolean,
)

data class ManagerFeed(
    val items: List<ManagerFeedItem>
)

data class ManagerFeedItem(
    val managerImage: Image?,
    val fullName: String,
    val isLiked: Boolean,
    val points: String,
    val event: String,
    val substitutions: List<SubstitutionItem>,
    val managerId: String,
    val feedId: String,
)

data class SubstitutionItem(
    val inPlayer: String,
    val outPlayer: String,
)

data class DateAndWeek(
    val date: String,
    val week: String
)

data class SignUpModel(
    var firstname: String = "unknown",
    var lastname: String = "unknown",
    var email: String = "unknown",
    var username: String = "unknown",
    var country: String = "unknown",
    var password: String = "unknown"
)

data class ManagerFollowers(
    val managerFollowers: List<ManagerFollower>
)

data class ManagerFollower(
    val fullName: String,
    val managerImage: String?,
    val managerId: String,
    val isFollowed: Boolean,
)

data class ManagerFollowings(
    val managerFollowings: List<ManagerFollowing>
)

data class ManagerFollowing(
    val fullName: String,
    val managerImage: String?,
    val managerId: String,
)

data class ManagerInfo(
    val fullName: String,
    val managerImage: Image?,
    val age: Int,
    val country: String,
    val points: String,
)

data class ManagerProfileInfo(
    val firstName: String,
    val lastName: String,
    val email: String,
    val country: String,
    val username: String,
    val imageUrl: String?,
)