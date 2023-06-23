package pt.epcc.alunos.al220007.bloqs.db

import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import jvmdbhelper.DBHelper
import jvmdbhelper.db_defenitions.DB
import jvmdbhelper.db_defenitions.Migration
import jvmdbhelper.db_defenitions.Migrations
import jvmdbhelper.db_defenitions.Seeders
import pt.epcc.alunos.al220007.bloqs.models.UserManager

object Database : DB() {
    private const val NAME = "bloqs"
    private const val VERSION = 6u

    override val migrations: Migrations
        get() = this.migrations()
    override val name: String
        get() = NAME
    override val seeders: Seeders
        get() = this.seeders()
    override val version: UInt
        get() = VERSION

    private fun migrations(): Migrations {
        val userManager = UserManager()

        return mapOf(
                6u to object : Migration {
                    override fun downgrade(dbh: DBHelper) {
                        try {
                            dbh.deleteTable(userManager)
                        } catch (_: SQLiteException) {

                        }
                    }

                    override fun upgrade(dbh: DBHelper) {
                        try {
                            dbh.createTable(userManager)
                        } catch (_: SQLiteException) {

                        }
                        Log.i("HAS CREATED", "userManager")
                    }
                }
        )
    }

    private fun seeders(): Seeders {
        return arrayOf()
    }

    fun createHelper(ctx: Context): Helper = Helper(ctx, this)
}
