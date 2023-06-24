package pt.epcc.alunos.al220007.bloqs.models.client;

import androidx.annotation.NonNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import pt.epcc.alunos.al220007.bloqs.core.Model;

public class Client implements Model {

private String name;
private byte age;
private URL image;
private long id;

@Override
public void fromMap(@NonNull Map<String, ?> map) {
	Object id = map.get(ClientManager.COL_ID);
	if (id != null) {
		this.id = Long.parseLong(String.valueOf(id));
	}

	Object name = map.get(ClientManager.COL_NAME);
	if (name instanceof CharSequence) {
		this.name = name.toString();
	}

	Object age = map.get(ClientManager.COL_AGE);
	if (age instanceof Number) {
		this.age = ((Number) age).byteValue();
	}

	Object image = map.get(ClientManager.COL_IMAGE);
	if (image instanceof CharSequence) {
		try {
			this.image = new URL(image.toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}

@NonNull
@Override
public Map<String, Object> getImmutable() {
	Map<String, Object> immutable = new HashMap<>();
	immutable.put(ClientManager.COL_ID, this.id);
	return immutable;
}

@NonNull
@Override
public Map<String, Object> getMutable() {
	Map<String, Object> mut = new HashMap<>();
	mut.put(ClientManager.COL_NAME, this.name);
	mut.put(ClientManager.COL_AGE, this.age);
	mut.put(ClientManager.COL_IMAGE, this.image);
	return mut;
}

@Override
public long getId() {
	return this.id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public byte getAge() {
	return age;
}

public void setAge(byte age) {
	this.age = age;
}

public URL getImage() {
	return image;
}

public void setImage(URL image) {
	this.image = image;
}
}
