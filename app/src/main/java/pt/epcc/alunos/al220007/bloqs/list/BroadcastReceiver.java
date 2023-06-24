package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Objects;

import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.core.Model;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

abstract public class BroadcastReceiver<T extends Model> extends android.content.BroadcastReceiver {

public static final String VERIFY_DB_ACTION = "pt.epcc.alunos.al220007.bloqs.list.VERIFY_DB";
public static final String REDIRECT = "redirect";
private final String TAG = this.getClass().getSimpleName();

abstract public @NonNull ModelManager<T> createManager();

abstract public @NonNull Class<IntentService<T>> createServiceClass();

abstract public @NonNull Class<Activity<T>> createRedirectActivityClass();

@Override
public void onReceive(Context context, Intent intent) {
	if (!Objects.equals(intent.getAction(), VERIFY_DB_ACTION)) {
		Log.d(this.TAG, "onReceive -> Not supported intent action");
		return;
	}

	Helper db = Database.INSTANCE.createHelper(context);

	boolean has = false;
	try {
		for (Object ignored :
		 this.createManager().read(db.readProxy(), new HashMap<>())) {
			has = true;
			break;
		}
	} catch (SQLiteException ignored) {
	}

	Log.d(this.TAG, "onReceive -> Has data?:\t" + has);
	if (!has) {
		context.startService(new Intent(context, this.createServiceClass()));
		return;
	}

	db.close();

	boolean redirect = has && intent.getBooleanExtra(REDIRECT, false);
	Log.d(this.TAG, "onReceive -> Will redirect?:\t" + redirect);
	if (redirect) {
		Intent i = new Intent(context, this.createRedirectActivityClass());
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	Log.i(this.TAG, "onReceive");
}
}
