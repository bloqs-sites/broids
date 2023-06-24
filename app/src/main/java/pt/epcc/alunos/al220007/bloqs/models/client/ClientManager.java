package pt.epcc.alunos.al220007.bloqs.models.client;

import android.util.Log;

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

public class ClientManager extends ModelManager<Client> {
public static final String NAME = "client";
public static final String COL_ID = "_id";
public static final String COL_NAME = "name";
public static final String COL_AGE = "age";
public static final String COL_IMAGE = "image";
public static final String URL = "https://www.dropbox.com/s/brj3s1aenj07icm/desafio.json?dl=1";

@NonNull
@Override
public String getName() {
	return NAME;
}

@NonNull
@Override
public Client init() {
	return new Client();
}

@Override
protected void manageTable(@NonNull Table table) {
	Column name = new Column(COL_NAME, Type.TXT);
	name.setNullable(false);
	name.setUnique(true);

	Column age = new Column(COL_AGE, Type.INT);
	age.setNullable(false);

	Column img = new Column(COL_IMAGE, Type.TXT);
	img.setNullable(false);

	table.addPK(COL_ID, true).addColumn(name, age, img);

	Log.d("TABLE", table + "\n" + table.getCreate());
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
		return json.getJSONObject("clientes").getJSONArray("cliente");
	} catch (JSONException e) {
		return new JSONArray();
	}
}

@Override
@NonNull
public Client fromJson(JSONObject json, Client o) {
	try {
		o.setName(json.getString("nome"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setAge((byte) json.getInt("idade"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setImage(new URL(json.getString("url_foto")));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	} catch (MalformedURLException e) {
		o.setImage(null);
	}

	return o;
}
}
