package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONArrayIterator<T> implements Iterator<T>, Iterable<T> {
private final JSONArray array;
private final JSONArrayIterator.Instanciator<T> init;
private int counter;

public JSONArrayIterator(JSONArray array, JSONArrayIterator.Instanciator<T> init) {
	this.array = array;
	this.init = init;
	this.counter = 0;
}

public interface Instanciator<T> {
	@NonNull T init();
	@NonNull T fromJson(JSONObject json, @NonNull T o);
}

@Override
public boolean hasNext() {
	return this.array.length() > this.counter;
}

@Override
public T next() {
	try {
		return this.init.fromJson(this.array.getJSONObject(this.counter++), this.init.init());
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}
}

@NonNull
@Override
public Iterator<T> iterator() {
	return new JSONArrayIterator<>(this.array, this.init);
}
}
