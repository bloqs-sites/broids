package pt.epcc.alunos.al220007.bloqs.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import jvmdbhelper.DBHelper
import jvmdbhelper.db_defenitions.DB

class Helper(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    private lateinit var db: DB

    constructor(context: Context?, db: DB) : this(context, db.name, null, db.version.toInt()) {
        this.db = db
        val writableDatabase = this.writableDatabase
        writableDatabase.close()
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(this.toString(), "Helper::onCreate")
        this.db.create(helper(db))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("onUpgrade", "oldVersion:\t $oldVersion\nnewVersion:\t $newVersion")
        this.db.migrate(helper(db), oldVersion.toUInt(), newVersion.toUInt())
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db == null) {
            return
        }

        this.db.migrate(this.helper(db), oldVersion.toUInt(), newVersion.toUInt())
    }

    private fun proxy(db: SQLiteDatabase): Proxy {
        return Proxy(db)
    }

    fun readProxy(): Proxy {
        return Proxy(this.readableDatabase)
    }

    fun writeProxy(): Proxy {
        return Proxy(this.writableDatabase)
    }

    private fun helper(db: SQLiteDatabase): DBHelper {
        return DBHelper(this.proxy(db))
    }

    fun readHelper(): DBHelper {
        return DBHelper(this.readProxy())
    }

    fun writeHelper(): DBHelper {
        return DBHelper(this.writeProxy())
    }

    override fun toString(): String {
        return "${this.db.name} [v${this.db.version}]: ${super.toString()}"
    }
}
