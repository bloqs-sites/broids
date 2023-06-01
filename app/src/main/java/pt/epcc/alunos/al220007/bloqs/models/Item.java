package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import jvmdbhelper.model.Model;

public class Item implements Model {

public String title;
public URI uri;
public boolean state;
private long id;

@Override
public void fromMap(@NonNull Map<String, ?> map) {
	Object id = map.get(ItemManager.COL_ID);
	if (id != null) {
		this.id = Long.parseLong(String.valueOf(id));
	}
	Object title = map.get(ItemManager.COL_TITLE);
	if (title instanceof CharSequence) {
		this.title = title.toString();
	}
	Object uri = map.get(ItemManager.COL_URI);
	if (uri instanceof CharSequence) {
		try {
			this.uri = new URI(uri.toString());
		} catch (URISyntaxException e) {
			this.uri = null;
		}
	}
	Object state = map.get(ItemManager.COL_STATE);
	if (state instanceof Number) {
		this.state = ((Number) state).shortValue() == 1;
	}
}

@NonNull
@Override
public Map<String, Object> getImmutable() {
	Map<String, Object> immutable = new HashMap<>();
	immutable.put(ItemManager.COL_ID, this.id);
	return immutable;
}

@NonNull
@Override
public Map<String, Object> getMutable() {
	Map<String, Object> mut = new HashMap<>();
	mut.put(ItemManager.COL_TITLE, this.title);
	if (this.uri != null) {
		mut.put(ItemManager.COL_URI, this.uri.toString());
	}
	mut.put(ItemManager.COL_STATE, this.state ? 1 : 0);
	return mut;
}

public long getId() {
	return id;
}

@NonNull
@Override
public String toString() {
	return "Item{" +
		"title='" + title + '\'' +
		", uri=" + uri +
		", state=" + state +
		", id=" + id +
		'}';
}
}
