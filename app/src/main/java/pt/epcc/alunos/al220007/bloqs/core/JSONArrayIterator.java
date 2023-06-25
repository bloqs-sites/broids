package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Iterator;

import jvmdbhelper.model.Model;

public class JSONArrayIterator<T extends Model> implements Iterator<T>, Iterable<T> {
private final JSONArray array;
private final ModelManager<T> manager;
private int counter;

public JSONArrayIterator(JSONArray array, ModelManager<T> manager) {
	this.array = array;
	this.manager = manager;
	this.counter = 0;
}

@Override
public boolean hasNext() {
	return this.array.length() > this.counter;
}

@Override
public T next() {
	try {
		return this.manager.fromJson(this.array.getJSONObject(this.counter++), this.manager.init());
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}
}

@NonNull
@Override
public Iterator<T> iterator() {
	return new JSONArrayIterator<>(this.array, this.manager);
}
}
