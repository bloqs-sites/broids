package pt.epcc.alunos.al220007.bloqs.db

import android.database.sqlite.SQLiteException
import jvmdbhelper.DBHelper
import jvmdbhelper.DBProxy
import jvmdbhelper.db_defenitions.DB
import jvmdbhelper.db_defenitions.Migration
import jvmdbhelper.db_defenitions.Migrations
import jvmdbhelper.db_defenitions.Seeder
import jvmdbhelper.db_defenitions.Seeders
import pt.epcc.alunos.al220007.bloqs.models.Item
import pt.epcc.alunos.al220007.bloqs.models.ItemManager
import pt.epcc.alunos.al220007.bloqs.models.Tag
import java.net.URI

object Database : DB() {
    private const val NAME = "bloqs"
    private const val VERSION = 3u

    override val migrations: Migrations
        get() = this.migrations()
    override val name: String
        get() = NAME
    override val seeders: Seeders
        get() = this.seeders()
    override val version: UInt
        get() = VERSION

    private fun migrations(): Migrations {
        val itemManager = ItemManager()

        return mapOf(
            3u to object : Migration {
                override fun downgrade(dbh: DBHelper) {
                    try {
                        dbh.deleteTable(itemManager, Tag)
                    } catch (_: SQLiteException) {

                    }
                }

                override fun upgrade(dbh: DBHelper) {
                    dbh.createTable(itemManager, Tag)
                }
            },
        )
    }

    private fun seeders(): Seeders {
        val itemManager = ItemManager()

        return arrayOf(
            object : Seeder {
                var apple = Item().apply {
                    fromMap(
                        mapOf(
                            ItemManager.COL_TITLE to "Apple",
                            ItemManager.COL_URI to "https://www.apple.com/",
                            ItemManager.COL_STATE to false
                        )
                    )
                }

                var pear = Item().apply {
                    this.title = "Pear"
                    this.uri = URI("https://pear.php.net/")
                    this.state = true
                }

                override fun seed(proxy: DBProxy) {
                    itemManager.create(proxy, apple)
                    itemManager.create(proxy, pear)
                }
            },
            object : Seeder {
                override fun seed(proxy: DBProxy) {
                    Tag.create(proxy, Tag().apply { name = "Programming" })
                    Tag.create(proxy, Tag().apply { name = "Fruits" })
                }
            }
        )
    }
}