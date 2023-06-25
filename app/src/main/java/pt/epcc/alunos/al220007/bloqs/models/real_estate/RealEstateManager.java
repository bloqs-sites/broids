package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import jvmdbhelper.db_defenitions.Column;
import jvmdbhelper.db_defenitions.Table;
import jvmdbhelper.db_defenitions.Type;
import pt.epcc.alunos.al220007.bloqs.core.ModelManager;

public class RealEstateManager extends ModelManager<RealEstate> {
public static final String NAME = "real_estate";
public static final String COL_ID = "_id";
public static final String COL_DESCRIPTION = "description";
public static final String COL_TYPOLOGY = "typology";
public static final String COL_LOCATION = "location";
public static final String COL_PHOTO = "photo";
public static final String URL = "https://www.dropbox.com/s/brj3s1aenj07icm/desafio.json?dl=1";

@NonNull
@Override
public String getName() {
	return NAME;
}

@NonNull
@Override
public RealEstate init() {
	return new RealEstate();
}

@Override
protected void manageTable(@NonNull Table table) {
	Column description = new Column(COL_DESCRIPTION, Type.TXT);
	description.setNullable(true);

	Column typology = new Column(COL_TYPOLOGY, Type.INT);
	typology.setNullable(false);

	Column location = new Column(COL_LOCATION, Type.TXT);
	location.setNullable(false);

	Column photo = new Column(COL_PHOTO, Type.TXT);
	photo.setNullable(false);

	table.addPK(COL_ID, true).addColumn(description, typology, location, photo);
}

@NonNull
@Override
public URL createResourceURL() {
	try {
		return new URL(URL);
	} catch (MalformedURLException e) {
		throw new RuntimeException(e);
	}
}

@NonNull
@Override
public JSONArray selectJsonArray(JSONObject json) {
	try {
		return json.getJSONObject("imoveis").getJSONArray("imovel");
	} catch (JSONException e) {
		return new JSONArray();
	}
}

@Override
@NonNull
public RealEstate fromJson(JSONObject json, RealEstate o) {
	try {
		o.setDescription(json.getString("descricao"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setTypology(Byte.parseByte(json.getString("tipologia").substring(1)));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setLocation(json.getString("localizacao"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setPhoto(new URL(json.getString("url_foto")));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	} catch (MalformedURLException e) {
		o.setPhoto(null);
	}

	return o;
}
}
