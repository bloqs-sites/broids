package pt.epcc.alunos.al220007.bloqs;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.ModelManager;
import pt.epcc.alunos.al220007.bloqs.models.User;
import pt.epcc.alunos.al220007.bloqs.models.UserManager;

public class Service extends IntentService implements Response.Listener<JSONObject>, Response.ErrorListener {
public static String NAME = "Service";
public static String URL = "https://www.dropbox.com/s/brj3s1aenj07icm/desafio.json?dl=1";

public Service() {
	super(NAME);
}

@Override
protected void onHandleIntent(@Nullable Intent intent) {
	RequestQueue queue = Volley.newRequestQueue(this);

	java.net.URL api;
	try {
		api = new URL(URL);
	} catch (MalformedURLException e) {
		throw new RuntimeException(e);
	}

	JsonObjectRequest req = new JsonObjectRequest(
	 Request.Method.GET,
	 api.toString(),
	 null,
	 this,
	 this
	);

	queue.add(req);
}

@Override
public void onResponse(JSONObject response) {
	List<User> users = new ArrayList<>();
	JSONArray usersArray;

	ModelManager<User> manager = new UserManager();
	try {
		usersArray = response.getJSONArray(manager.category());
	} catch (JSONException e) {
		throw new RuntimeException(e);
	}

	Helper db = new Helper(this, Database.INSTANCE);

	manager.populateFromJson(db, usersArray);

	db.close();
}

@Override
public void onErrorResponse(VolleyError error) {

}
}
