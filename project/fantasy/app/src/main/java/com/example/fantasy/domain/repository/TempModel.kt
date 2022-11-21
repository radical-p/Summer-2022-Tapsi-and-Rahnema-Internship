package com.example.fantasy.domain.repository

data class Dashboard(
    val username: String,
    val credits: String,
    val team: List<Player>,
    val remainingPlayers: Int,
    var currentPlayerIndex: Int,
)

data class PlayersInfoList(
    val total: Int,
    val playersList: List<Player>
)