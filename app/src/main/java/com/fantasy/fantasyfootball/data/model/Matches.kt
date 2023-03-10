package com.fantasy.fantasyfootball.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fantasy.fantasyfootball.constant.Enums

@Entity
data class Matches(
    @PrimaryKey(autoGenerate = true)
    val matchId: Int? = null,
    val homeTeam: Enums.Team,
    val awayTeam: Enums.Team,
    val homeScore: Int,
    val awayScore: Int,
    val date: String,
)