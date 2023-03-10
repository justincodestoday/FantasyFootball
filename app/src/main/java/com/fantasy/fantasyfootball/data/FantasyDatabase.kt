package com.fantasy.fantasyfootball.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fantasy.fantasyfootball.data.model.*

@Database(entities = [User::class, Team::class, Player::class, Matches::class, FantasyPlayer::class], version = 1)
abstract class FantasyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val playerDao: PlayerDao
    abstract val teamDao: TeamDao
    abstract val matchDao: MatchDao

    companion object {
        const val DATABASE_NAME = "fantasy_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
            }
        }
    }
}