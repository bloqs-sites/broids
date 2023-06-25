package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import androidx.annotation.NonNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jvmdbhelper.model.Model;
import pt.epcc.alunos.al220007.bloqs.models.characteristics.Characteristics;

public class RealEstate implements Model {

private String description;
private byte typology;
private String location;
private URL photo;
private List<Characteristics> characteristics;
private long id;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

@Override
public void fromMap(@NonNull Map<String, ?> map) {
	Object id = map.get(RealEstateManager.COL_ID);
	if (id != null) {
		this.id = Long.parseLong(String.valueOf(id));
	}
	Object description = map.get(RealEstateManager.COL_DESCRIPTION);
	if (description instanceof CharSequence) {
		this.description = description.toString();
	}
	Object typology = map.get(RealEstateManager.COL_TYPOLOGY);
	if (typology instanceof Number) {
		this.typology = ((Number) typology).byteValue();
	}
	Object location = map.get(RealEstateManager.COL_LOCATION);
	if (location instanceof CharSequence) {
		this.location = location.toString();
	}
	Object photo = map.get(RealEstateManager.COL_PHOTO);
	if (photo instanceof CharSequence) {
		try {
			this.photo = new URL(photo.toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}

@NonNull
@Override
public Map<String, Object> getImmutable() {
	Map<String, Object> immutable = new HashMap<>();
	immutable.put(RealEstateManager.COL_ID, this.id);
	return immutable;
}

@NonNull
@Override
public Map<String, Object> getMutable() {
	Map<String, Object> mut = new HashMap<>();
	mut.put(RealEstateManager.COL_DESCRIPTION, this.description);
	mut.put(RealEstateManager.COL_TYPOLOGY, this.typology);
	mut.put(RealEstateManager.COL_LOCATION, this.location);
	mut.put(RealEstateManager.COL_PHOTO, this.photo);
	return mut;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public byte getTypology() {
	return typology;
}

public void setTypology(byte typology) {
	this.typology = typology;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public URL getPhoto() {
	return photo;
}

public void setPhoto(URL photo) {
	this.photo = photo;
}

public List<Characteristics> getCharacteristics() {
	return characteristics;
}

public void setCharacteristics(List<Characteristics> characteristics) {
	this.characteristics = characteristics;
}
}
