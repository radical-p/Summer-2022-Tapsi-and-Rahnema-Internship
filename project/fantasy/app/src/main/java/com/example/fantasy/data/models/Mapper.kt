package com.example.fantasy.data.models

import com.example.fantasy.data.response.LoginModelResponse
import com.example.fantasy.data.response.LoginModelVars
import com.example.fantasy.data.response.VerificationResponse
import com.example.fantasy.data.response.VerificationResponseVars
import com.example.fantasy.domain.repository.*

fun PlayerModelDto.toPlayer(): Player {
    return Player(
        price = playerPrice,
        rating = playerRating,
        name = playerName,
        team = playerTeam.toTeamString(),
        id = playerId,
        isInTeam = false,
    )
}

fun PlayersDataDto.toPlayerModel(): PlayersInfoList {
    return PlayersInfoList(
        total = total,
        playersList = data.map {player->
        PlayerModelDto(
            playerName = player.playerName,
            playerPrice = player.playerPrice,
            playerRating = player.playerRating,
            playerTeam = player.playerTeam,
            playerId = player.playerId,
        ).toPlayer() })
}

fun SearchPlayerDataDto.toPlayerModel(): PlayersInfoList {
    return PlayersInfoList(
        total = data.total,
        playersList = data.playersList.map { player ->
            PlayerModelDto(
                playerName = player.playerName,
                playerPrice = player.playerPrice,
                playerRating = player.playerRating,
                playerTeam = player.playerTeam,
                playerId = player.playerId,
            ).toPlayer()
        }
    )
}

fun PlayerTeamModelDto.toTeamString(): String {
    return team
}

fun DateAndWeekModelDto.toDateAndWeekFields(): DateAndWeekFieldsDto{
    return DateAndWeekFieldsDto(
        date = data.date,
        week = data.week
    )
}

fun DateAndWeekFieldsDto.toDateAndWeek(): DateAndWeek {
    return DateAndWeek(
        date = date,
        week = week
    )
}

fun UpdatedTeamDto.toPlayerIdsDtoList(): List<PlayerIdsDto> {
    return data.map {
        PlayerIdsDto(
            playerId = it.playerId
        )
    }
}

fun LoginModelResponse.toLoginModelVars(): LoginModelVars {
    return LoginModelVars(
        managerId = data.managerId,
        token = data.token
    )
}

fun VerificationResponse.toVerificationModelVars(): VerificationResponseVars {
    return VerificationResponseVars(
        token = data.token
    )
}

fun ManagerFeedDto.toManagerFeed(): ManagerFeed {
    return ManagerFeed(
        items = ManagerFeedList.map {
            ManagerFeedItem(
                managerImage = it.managerImage,
                fullName = it.firstName + " " + it.lastName,
                isLiked = it.isLiked,
                points = it.points,
                event = it.event,
                substitutions = it.substitutions.map { singleSubstitutionItem ->
                    SubstitutionItem(
                        inPlayer = singleSubstitutionItem.inPlayer,
                        outPlayer = singleSubstitutionItem.outPlayer,
                    )
                },
                managerId = it.managerId,
                feedId = it.feedId,
            )
        }
    )
}

fun ManagerFollowersDto.toManagerFollowers(): ManagerFollowers {
    return ManagerFollowers(
        managerFollowers =  managerFollowers.map {
            ManagerFollower(
                fullName = it.firstName + " " + it.lastName,
                managerImage = it.managerImage,
                managerId = it.managerId,
                isFollowed = it.isFollowed
            )
        }
    )
}

fun ManagerFollowingsDto.toManagerFollowings(): ManagerFollowings {
    return ManagerFollowings(
        managerFollowings = managerFollowings.map {
            ManagerFollowing(
                fullName = it.firstName + " " + it.lastName,
                managerImage = it.managerImage,
                managerId = it.managerId,
            )
        }
    )
}

fun ManagerInfoDto.toManagerInfo(): ManagerInfo {
    return ManagerInfo(
        fullName = managerInfoItems.fullName,
        managerImage = managerInfoItems.managerImage,
        age = managerInfoItems.age,
        country = managerInfoItems.country,
        points = managerInfoItems.points,
    )
}

fun ManagerProfileDataDto.toManagerProfile(): ManagerProfileInfo {
    return ManagerProfileInfo(
        firstName = data.manager.firstName,
        lastName = data.manager.lastName,
        email = data.manager.email,
        country = data.manager.country,
        username = data.manager.username,
        imageUrl = data.manager.image.imageUrl,
    )
}