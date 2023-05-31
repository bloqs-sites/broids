package pt.epcc.alunos.al220007.bloqs.storage;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import jvmdbhelper.DBProxy;

public class Proxy implements DBProxy {
final SQLiteDatabase real;

public Proxy(SQLiteDatabase real) {
	this.real = real;
}

@Override
public void close() {
	if (this.real != null && this.real.isOpen()) {
		this.real.close();
	}
}

@Override
public void exec(@NonNull String s, @Nullable List<?> list) {
	this.real.execSQL(s, list == null ? new Object[]{} : list.toArray());
}
}
