package pt.epcc.alunos.al220007.bloqs.storage;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import jvmdbhelper.DBHelper;
import jvmdbhelper.DBProxy;

public class Helper extends DBHelper {
public Helper(SQLiteDatabase db) {
	this(new Proxy(db));
}

private Helper(@NonNull DBProxy proxy) {
	super(proxy);
}
}
