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
	this.id = (long) map.get(ItemManager.COL_ID);
	this.title = String.valueOf(map.get(ItemManager.COL_TITLE));
	try {
		this.uri = new URI((String) map.get(ItemManager.COL_URI));
	} catch (URISyntaxException e) {
		this.uri = null;
	}
	this.state = (boolean) map.get(ItemManager.COL_STATE);
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
	mut.put(ItemManager.COL_URI, this.uri);
	mut.put(ItemManager.COL_STATE, this.state);
	return mut;
}
}
