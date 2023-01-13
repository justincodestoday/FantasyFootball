package com.fantasy.fantasyfootball.model

import java.util.*

data class FantasyTeam(
    var name: String? = null,
    var points: Int = 0,
    var remainingBudget: Float = 0f,
    var lastUpdated: Date? = null,

    var gk: FantasyPlayer? = null,
    var lb: FantasyPlayer? = null,
    var lcb: FantasyPlayer? = null,
    var rcb: FantasyPlayer? = null,
    var rb: FantasyPlayer? = null,
    var lm: FantasyPlayer? = null,
    var lcm: FantasyPlayer? = null,
    var rcm: FantasyPlayer? = null,
    var rm: FantasyPlayer? = null,
    var ls: FantasyPlayer? = null,
    var rs: FantasyPlayer? = null
)
