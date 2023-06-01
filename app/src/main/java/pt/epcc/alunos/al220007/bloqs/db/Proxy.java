package pt.epcc.alunos.al220007.bloqs.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jvmdbhelper.DBProxy;
import jvmdbhelper.model.Model;
import jvmdbhelper.model.TableManager;

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
public long exec(@NonNull String s, @NonNull List<?> list) {
	this.real.execSQL(s, list.toArray());
	return Long.MIN_VALUE;
}

@NonNull
@Override
public <T extends Model> Iterable<T> query(@NonNull String s, @NonNull List<?> list, @NonNull TableManager<T> tableManager) {
	Cursor cursor;
	String[] args = new String[list.size()];
	int i = 0;
	for (Object o :
		list) {
		args[i++] = String.valueOf(o);
	}
	try {
		cursor = this.real.rawQuery(s, args);
	} catch (ClassCastException e) {
		throw new RuntimeException(e);
	}

	List<T> models = new ArrayList<>();
	if (cursor != null && cursor.moveToFirst()) {
		do {
			String[] cols = cursor.getColumnNames();
			Map<String, Object> values = new HashMap<>();

			for (String col :
				cols) {
				switch (tableManager.getColType(col)) {
					case TXT:
						values.put(col, cursor.getString(cursor.getColumnIndexOrThrow(col)));
						break;
					case INT:
						values.put(col, cursor.getInt(cursor.getColumnIndexOrThrow(col)));
						break;
					default:
						// Not Implemented
						break;
				}
			}

			T item = tableManager.init();
			item.fromMap(values);
			models.add(item);
		} while (cursor.moveToNext());
	} else {
		return models;
	}

	cursor.close();

	return models;
}
}
