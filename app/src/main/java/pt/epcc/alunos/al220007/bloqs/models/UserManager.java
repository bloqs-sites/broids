package pt.epcc.alunos.al220007.bloqs.models;

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
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

public class UserManager extends ModelManager<User> {
public static final String NAME = "users";
public static final String COL_ID = "_id";
public static final String COL_USER = "user";
public static final String COL_PASS = "pass";
public static final String URL = "https://www.dropbox.com/s/brj3s1aenj07icm/desafio.json?dl=1";

@NonNull
@Override
public String getName() {
	return NAME;
}

@NonNull
@Override
public User init() {
	return new User();
}

@Override
protected void manageTable(@NonNull Table table) {
	Column user = new Column(COL_USER, Type.TXT);
	user.setNullable(false);
	user.setUnique(true);

	Column pass = new Column(COL_PASS, Type.TXT);
	pass.setNullable(false);

	table.addPK(COL_ID, true).addColumn(user, pass);

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
		return json.getJSONArray("users");
	} catch (JSONException e) {
		return new JSONArray();
	}
}

@Override
@NonNull
public User fromJson(JSONObject json) {
	User o = new User();
	try {
		o.setUser(json.getString("user"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	try {
		o.setPass(json.getString("password"));
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	return o;
}
}
