package pt.epcc.alunos.al220007.bloqs.db

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
    private const val VERSION = 2u

    override fun migrations(): Migrations {
        val itemManager = ItemManager()

        return mapOf(
            1u to object : Migration {
                override fun downgrade(dbh: DBHelper) {
                    dbh.deleteTable(itemManager, Tag)
                }

                override fun upgrade(dbh: DBHelper) {
                    dbh.createTable(itemManager, Tag)
                }
            },
            2u to object : Migration {
                override fun downgrade(dbh: DBHelper) {

                }

                override fun upgrade(dbh: DBHelper) {

                }
            }
        )
    }

    override fun seeders(): Seeders {
        val itemManager = ItemManager()

        return mapOf(
            1u to object : Seeder {
                override fun seed(proxy: DBProxy) {

                }
            },
            2u to object : Seeder {
                var apple = Item().apply {
                    fromMap(
                        mapOf(
                            ItemManager.COL_TITLE to "Apple",
                            ItemManager.COL_URI to URI("https://www.apple.com/"),
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
            }
        )
    }

    override fun name(): String {
        return NAME
    }

    override fun version(): UInt {
        return VERSION
    }
}