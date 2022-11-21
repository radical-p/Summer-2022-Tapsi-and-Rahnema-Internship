package com.example.fantasy.data.models

import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.Player

fun DashboardModelDto.toDashboard(): Dashboard {
    return Dashboard(
        remainingPlayers = 15 - remainingPlayers,
        username = manager.username,
        credits = manager.credits,
        currentPlayerIndex = 15,
        team = manager.team.team.map {
            it.toInTeamSinglePlayerModelDto().toPlayer()
        }
    )
}

fun DashboardDataDto.toDashboardModelDto(): DashboardModelDto {
    return DashboardModelDto(
        remainingPlayers = data.remainingPlayers,
        manager = data.manager
    )
}

fun InTeamPlayerModelDto.toInTeamSinglePlayerModelDto(): InTeamSinglePlayerModelDto {
    return if(player != null) {
        InTeamSinglePlayerModelDto(
            name = player.name,
            rating = player.rating,
            price = player.price,
            id = player.id,
            isInTeam = true,
        )
    } else {
        InTeamSinglePlayerModelDto(
            name = "-",
            rating = "-",
            price = "-",
            id = "-",
            isInTeam = false,
        )
    }
}

fun InTeamSinglePlayerModelDto.toPlayer(): Player {
    return Player(
        id = id,
        price = price,
        rating = rating,
        name = name,
        team = "",
        isInTeam = isInTeam,
    )
}
