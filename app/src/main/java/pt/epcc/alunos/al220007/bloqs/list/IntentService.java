package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.core.Model;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

public abstract class IntentService<T extends Model> extends android.app.IntentService implements Response.Listener<JSONObject>, Response.ErrorListener {
public static final String NAME = "Service";

private final ModelManager<T> manager = this.createManager();

public IntentService() {
	super(NAME);
}

abstract public ModelManager<T> createManager();

@Override
protected void onHandleIntent(@Nullable Intent intent) {
	RequestQueue queue = Volley.newRequestQueue(this);

	JsonObjectRequest req = new JsonObjectRequest(
		Request.Method.GET,
		this.manager.createResourceURL().toString(),
		null,
		this,
		this
	);

	queue.add(req);
}

@Override
public void onResponse(JSONObject response) {
	Helper db = Database.INSTANCE.createHelper(this);

	manager.populateFromJson(db, this.manager.selectJsonArray(response));

	db.close();
}

@Override
public void onErrorResponse(VolleyError error) {

}
}
