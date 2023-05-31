package pt.epcc.alunos.al220007.bloqs.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import jvmdbhelper.DBHelper;
import jvmdbhelper.db_defenitions.DB;

public class AndroidDBHelper extends SQLiteOpenHelper {
private DB db;

public AndroidDBHelper(@Nullable Context context, DB db) {
	this(context, db.name(), null, 1);
	this.db = db;
}

private AndroidDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
	super(context, name, factory, version);
}

@Override
public void onCreate(SQLiteDatabase db) {
	this.db.doCreation(this.createHelper(db));
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	this.db.doMigration(this.createHelper(db));
}

private DBHelper createHelper(SQLiteDatabase db) {
	return new Helper(db);
}
}
