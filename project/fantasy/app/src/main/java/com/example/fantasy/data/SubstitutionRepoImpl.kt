package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.SubstitutionApi
import com.example.fantasy.data.models.SubstitutionInfo
import com.example.fantasy.domain.repository.SubstitutionRepo

class SubstitutionRepoImpl(
    private val dataStore: Storage,
    private val substitutionApi: SubstitutionApi,
): SubstitutionRepo {
    override suspend fun substitutePlayer(inPlayer: Int, outPlayer: Int) {
        val token = dataStore.get("token")
        return substitutionApi.substitutePlayer(token!!, SubstitutionInfo(inPlayer, outPlayer))
    }
}