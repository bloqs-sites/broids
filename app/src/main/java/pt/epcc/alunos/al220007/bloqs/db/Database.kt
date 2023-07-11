package pt.epcc.alunos.al220007.bloqs.db

import android.content.Context
import jvmdbhelper.db_defenitions.DB
import jvmdbhelper.db_defenitions.Migrations
import jvmdbhelper.db_defenitions.Seeders

object Database : DB() {
    private const val NAME = "bloqs"
    private const val VERSION = 1u

    override val migrations: Migrations
        get() = this.migrations()
    override val name: String
        get() = NAME
    override val seeders: Seeders
        get() = this.seeders()
    override val version: UInt
        get() = VERSION

    private fun migrations(): Migrations {
        return mapOf()
    }

    private fun seeders(): Seeders {
        return arrayOf()
    }

    fun createHelper(ctx: Context): Helper = Helper(ctx, this)
}
