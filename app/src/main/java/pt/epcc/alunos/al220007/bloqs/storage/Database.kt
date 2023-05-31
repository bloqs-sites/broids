package pt.epcc.alunos.al220007.bloqs.storage

import jvmdbhelper.DBHelper
import jvmdbhelper.db_defenitions.DB
import jvmdbhelper.db_defenitions.Migration
import jvmdbhelper.db_defenitions.Migrations
import pt.epcc.alunos.al220007.bloqs.models.ItemManager

object Database : DB() {
    private const val NAME = "bloqs"
    const val VERSION = 1

    override fun genMigrations(): Migrations {
        val itemManager = ItemManager()

        return mapOf(
                1u to object : Migration {
                    override fun downgrade(dbh: DBHelper) {
                        dbh.deleteTable(itemManager)
                    }

                    override fun upgrade(dbh: DBHelper) {
                        dbh.createTable(itemManager)
                    }
                }
        )
    }

    override fun name(): String {
        return NAME
    }
}