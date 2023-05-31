package pt.epcc.alunos.al220007.bloqs.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import jvmdbhelper.DBHelper
import jvmdbhelper.db_defenitions.DB

class Helper(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    private var db: DB? = null

    constructor(context: Context?, db: DB) : this(context, db.name(), null, db.version().toInt()) {
        this.db = db
    }

    override fun onCreate(db: SQLiteDatabase) {
        this.db?.create(helper(db)) ?: throw Exception()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        this.db?.migrate(this.helper(db), oldVersion.toUInt(), newVersion.toUInt())
            ?: throw Exception()
    }

    private fun helper(db: SQLiteDatabase): DBHelper {
        return DBHelper(Proxy(db))
    }
}