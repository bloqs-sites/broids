package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import pt.epcc.alunos.al220007.bloqs.async.Volley;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.core.Model;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

public abstract class IntentService<T extends Model> extends android.app.IntentService implements Response.Listener<JSONObject>, Response.ErrorListener {

private final String TAG = this.getClass().getSimpleName();
public static final String NAME = "IntentService";

private final ModelManager<T> manager = this.createManager();

public IntentService() {
	super(NAME);
}

abstract public ModelManager<T> createManager();

abstract public @NonNull Class<Activity<T>> createRedirectActivityClass();

@Override
protected void onHandleIntent(@Nullable Intent intent) {
	Volley.getInstance(() -> this).queue(new JsonObjectRequest(
		Request.Method.GET,
		this.manager.createResourceURL().toString(),
		null,
		this,
		this
	));

	Log.i(this.TAG, "onHandleIntent");
}

@Override
public void onResponse(JSONObject response) {
	Helper db = Database.INSTANCE.createHelper(this);

	manager.populateFromJson(db, this.manager.selectJsonArray(response));

	db.close();

	Intent i = new Intent(this, this.createRedirectActivityClass());
	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	this.startActivity(i);

	Log.i(this.TAG, "onResponse");
}

@Override
public void onErrorResponse(VolleyError error) {
	Log.i(this.TAG, "onErrorResponse:\t" + error.toString());
}
}
