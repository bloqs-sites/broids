package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import jvmdbhelper.model.Model;
import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

abstract public class ModelManager<T extends Model> extends TableManager<T> {
abstract public @NonNull URL createResourceURL();

abstract public @NonNull JSONArray selectJsonArray(JSONObject json);

abstract public @NonNull T fromJson(JSONObject json, T o);

final public void populateFromJson(Helper db, JSONArray jsonArray) {
	Iterable<T> iterator = new JSONArrayIterator<>(jsonArray, this);
//	for (Iterator<T> it = iterator; it.hasNext(); ) {
//		T o = it.next();
	for (T o :
		iterator) {
		this.create(db.writeHelper().getProxy(), o);
	}
}
}
