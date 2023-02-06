package com.fantasy.fantasyfootball.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fantasy.fantasyfootball.constant.Enums
import com.fantasy.fantasyfootball.data.model.Player
import com.fantasy.fantasyfootball.data.model.TeamsWithPlayers
import com.fantasy.fantasyfootball.data.model.UserWithTeam
import com.fantasy.fantasyfootball.repository.PlayerRepository
import com.fantasy.fantasyfootball.repository.TeamRepository
import com.fantasy.fantasyfootball.repository.UserRepository
import kotlinx.coroutines.launch

class TeamManagementViewModel(
    private val playerRepo: PlayerRepository,
    private val teamRepo: TeamRepository,
    private val userRepo: UserRepository
) : ViewModel() {
    val userTeam: MutableLiveData<UserWithTeam> = MutableLiveData()
    val teamWithPlayers: MutableLiveData<TeamsWithPlayers> = MutableLiveData()
    val player: MutableLiveData<Player> = MutableLiveData()

    val players = listOf(
        Player(
            firstName = "Kylian",
            lastName = "Mbappe",
            team = "Paris Saint-Germain",
            teamConst = Enums.Team.ParisSG,
            price = 45.0f,
            area = Enums.Area.Striker,
            color = Enums.ShirtColor.DARKBLUE,
        ),
        Player(
            firstName = "Erling",
            lastName = "Haaland",
            team = "Manchester City",
            teamConst = Enums.Team.ManCity,
            price = 42.5f,
            area = Enums.Area.Striker,
            color = Enums.ShirtColor.LIGHTBLUE
        ),
        Player(
            firstName = "Mohamed",
            lastName = "Salah",
            team = "Liverpool FC",
            teamConst = Enums.Team.Liverpool,
            price = 20.0f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.DARKRED
        ),
        Player(
            firstName = "Jude",
            lastName = "Bellingham",
            team = "Borussia Dortmund",
            teamConst = Enums.Team.BorussiaDort,
            price = 27.5f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.YELLOW
        ),
        Player(
            firstName = "Phil",
            lastName = "Foden",
            team = "Manchester City",
            teamConst = Enums.Team.ManCity,
            price = 27.5f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.LIGHTBLUE
        ),
        Player(
            firstName = "Pedri",
            lastName = "",
            team = "FC Barcelona",
            teamConst = Enums.Team.Barcelona,
            price = 25.0f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.DARKBLUE
        ),
        Player(
            firstName = "Josko",
            lastName = "Gvardiol",
            team = "RB Leipzig",
            teamConst = Enums.Team.Leipzig,
            price = 19.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.WHITE
        ),
        Player(
            firstName = "Ruben",
            lastName = "Dias",
            team = "Manchester City",
            teamConst = Enums.Team.ManCity,
            price = 19.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.LIGHTBLUE
        ),
        Player(
            firstName = "Reece",
            lastName = "James",
            team = "Chelsea FC",
            teamConst = Enums.Team.Chelsea,
            price = 17.5f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.DARKBLUE
        ),
        Player(
            firstName = "Alphonso",
            lastName = "Davies",
            team = "Bayern Munich",
            teamConst = Enums.Team.BayernMunich,
            price = 17.5f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.LIGHTRED
        ),
        Player(
            firstName = "Thibaut",
            lastName = "Courtois",
            team = "Real Madrid",
            teamConst = Enums.Team.RealMadrid,
            price = 15.0f,
            area = Enums.Area.Goalkeeper,
            color = Enums.ShirtColor.WHITE
        ),
        Player(
            firstName = "Aaron",
            lastName = "Ramsdale",
            team = "Arsenal",
            teamConst = Enums.Team.Arsenal,
            price = 7.5f,
            area = Enums.Area.Goalkeeper,
            color = Enums.ShirtColor.LIGHTRED
        ),
        Player(
            firstName = "Nick",
            lastName = "Pope",
            team = "Newcastle United",
            teamConst = Enums.Team.Newcastle,
            price = 4.5f,
            area = Enums.Area.Goalkeeper,
            color = Enums.ShirtColor.BLACK
        ),
        Player(
            firstName = "Romelu",
            lastName = "Lukaku",
            team = "Inter Milan",
            teamConst = Enums.Team.InterMilan,
            price = 14.0f,
            area = Enums.Area.Striker,
            color = Enums.ShirtColor.DARKBLUE
        ),
        Player(
            firstName = "Julian",
            lastName = "Alvarez",
            team = "Manchester City",
            teamConst = Enums.Team.ManCity,
            price = 12.5f,
            area = Enums.Area.Striker,
            color = Enums.ShirtColor.LIGHTBLUE
        ),
        Player(
            firstName = "Allan",
            lastName = "Saint-Maximin",
            team = "Newcastle United",
            teamConst = Enums.Team.Newcastle,
            price = 10.0f,
            area = Enums.Area.Striker,
            color = Enums.ShirtColor.BLACK
        ),
        Player(
            firstName = "James",
            lastName = "Madison",
            team = "Leicester City",
            teamConst = Enums.Team.LeicesterCity,
            price = 14.0f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.LIGHTBLUE
        ),
        Player(
            firstName = "Sandro",
            lastName = "Tonali",
            team = "AC Milan",
            teamConst = Enums.Team.ACMilan,
            price = 12.5f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.DARKRED
        ),
        Player(
            firstName = "Fabinho",
            lastName = "",
            team = "Liverpool FC",
            teamConst = Enums.Team.Liverpool,
            price = 14.0f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.DARKRED
        ),
        Player(
            firstName = "Eduardo",
            lastName = "Camavinga",
            team = "Real Madrid",
            teamConst = Enums.Team.RealMadrid,
            price = 12.5f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.WHITE
        ),
        Player(
            firstName = "Carlos",
            lastName = "Soler",
            team = "Paris Saint-Germain",
            teamConst = Enums.Team.ParisSG,
            price = 9.0f,
            area = Enums.Area.Midfielder,
            color = Enums.ShirtColor.DARKBLUE
        ),
        Player(
            firstName = "David",
            lastName = "Alaba",
            team = "Real Madrid",
            teamConst = Enums.Team.RealMadrid,
            price = 14.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.WHITE
        ),
        Player(
            firstName = "Andrew",
            lastName = "Robertson",
            team = "Liverpool FC",
            teamConst = Enums.Team.Liverpool,
            price = 14.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.DARKRED
        ),
        Player(
            firstName = "Marc",
            lastName = "Cucurella",
            team = "Chelsea FC",
            teamConst = Enums.Team.Chelsea,
            price = 14.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.DARKBLUE
        ),
        Player(
            firstName = "Bremer",
            lastName = "",
            team = "Juventus FC",
            teamConst = Enums.Team.Juventus,
            price = 10.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.WHITE
        ),
        Player(
            firstName = "Benoit",
            lastName = "Badiashile",
            team = "Chelsea FC",
            teamConst = Enums.Team.Chelsea,
            price = 10.0f,
            area = Enums.Area.Defender,
            color = Enums.ShirtColor.DARKBLUE
        ),
    )

//    init {
//        viewModelScope.launch {
//            players.forEach {
//                playerRepo.createPlayer(it)
//            }
////            teamsAndPlayers.forEach {
////                teamRepo.addPlayers(it)
////            }
//        }
//    }

    fun getPlayersByArea(area: String) {
        viewModelScope.launch {
            playerRepo.getPlayersByArea(area)
        }
    }

//    fun getPlayerById(playerId: Int) {
//        viewModelScope.launch {
//            val res = playerRepo.getPlayerById(playerId)
//            res?.let {
//                player.value = it
//            }
//        }
//    }

    fun getTeamWithPlayersByTeamId(teamId:Int) {
        viewModelScope.launch {
            val res = teamRepo.getTeamWithPlayersByTeamId(teamId)
            res.let {
                teamWithPlayers.value = it
                Log.d("debug", "${it.players}")
            }
        }
    }

    fun getUserWithTeam(userId: Int) {
        viewModelScope.launch {
            val res = userRepo.getUserWithTeam(userId)
            res?.let {
                userTeam.value = it
            }
        }
    }

    class Provider(
        private val playerRepo: PlayerRepository,
        private val teamRepo: TeamRepository,
        private val userRepo: UserRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TeamManagementViewModel(playerRepo, teamRepo, userRepo) as T
        }
    }
}