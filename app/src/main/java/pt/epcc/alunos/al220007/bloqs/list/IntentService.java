package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import jvmdbhelper.model.Model;
import pt.epcc.alunos.al220007.bloqs.async.Volley;
import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

public abstract class IntentService<T extends Model> extends android.app.IntentService implements Response.Listener<JSONObject> {

public static final String NAME = "IntentService";
private final String TAG = this.getClass().getSimpleName();
private final ModelManager<T> manager = this.createManager();

public IntentService() {
	super(NAME);
}

abstract public ModelManager<T> createManager();

abstract public @NonNull Class<?> createRedirectClass();

@Override
protected void onHandleIntent(@Nullable Intent intent) {
	Request<JSONObject> req = new JsonObjectRequest(
		Request.Method.GET,
		this.manager.createResourceURL().toString(),
		null,
		this,
		(error) -> Log.i(this.TAG, "onErrorResponse:\t" + error.toString())
	);
	req.setTag(TAG);
	Volley.getInstance(() -> this).queue(req);

	Log.i(this.TAG, "onHandleIntent");
}

@Override
public void onResponse(JSONObject response) {
	Helper db = Database.INSTANCE.createHelper(this);

	manager.populateFromJson(db, this.manager.selectJsonArray(response));

	db.close();

	Intent i = new Intent(this, this.createRedirectClass());
	boolean show = i.getBooleanExtra(Activity.SHOW, false);
	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	Log.d(this.TAG, "onReceive -> Will show?:\t" + show);
	i.putExtra(Activity.SHOW, show);
	this.startActivity(i);

	Log.i(this.TAG, "onResponse");
}
}
