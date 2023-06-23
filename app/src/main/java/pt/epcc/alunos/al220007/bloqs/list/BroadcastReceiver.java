package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Objects;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.core.Model;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

abstract public class BroadcastReceiver<T extends Model> extends android.content.BroadcastReceiver {

public static final String VERIFY_DB_ACTION = "pt.epcc.alunos.al220007.bloqs.list.VERIFY_DB";
public static final String REDIRECT = "redirect";

abstract public @NonNull ModelManager<T> createManager();

abstract public @NonNull Class<IntentService<T>> createServiceClass();

abstract public @NonNull Class<Activity<T>> createRedirectActivityClass();

@Override
public void onReceive(Context context, Intent intent) {
	if (!Objects.equals(intent.getAction(), VERIFY_DB_ACTION)) {
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

	if (!has) {
		context.startService(new Intent(context, this.createServiceClass()));
	}

	db.close();

	if (has && intent.getBooleanExtra(REDIRECT, false)) {
		Intent i = new Intent(context, this.createRedirectActivityClass());
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
}
