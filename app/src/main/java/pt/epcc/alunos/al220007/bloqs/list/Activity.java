package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.ResourcesListActivity;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

abstract public class Activity<T extends jvmdbhelper.model.Model> extends AppCompatActivity implements View.OnClickListener, ActivityProxy {
public static final String SHOW = "show";
private static final int LAYOUT = R.layout.activity_models_list;
private static final int RECYCLER_VIEW = R.id.list;
public final String NO_ITEMS_MSG = "There are no `" + this.createManager().getName() + "` to show right now";
private final String TAG = this.getClass().getSimpleName();

private Button delete;

private List<T> list;

protected abstract @NonNull TableManager<T> createManager();

protected abstract @NonNull Adapter<T> createAdapter(List<T> list);

protected abstract @NonNull RecyclerView.LayoutManager createLayoutManager();

protected abstract @NonNull BroadcastReceiver<T> getBroadcastReceiver();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.register();

	this.delete = this.findViewById(R.id.delete);
	this.delete.setOnClickListener(this);

	Log.i(TAG, "onCreate");
}

@Override
protected void onResume() {
	super.onResume();

	this.register();

	if (this.getIntent().getBooleanExtra(SHOW, false)) {
		Helper db = Database.INSTANCE.createHelper(this);
		TableManager<T> manager = this.createManager();
		Button btn = this.findViewById(R.id.delete);
		btn.setOnClickListener(v -> {
			manager.delete(db.writeProxy(), manager.init());
			this.finish();
		});

		Iterable<T> items = manager.read(db.readProxy(), new HashMap<>());
		this.list = new ArrayList<>();
		for (T i :
			items) {
			this.list.add(i);
		}

		RecyclerView recyclerView = this.findViewById(RECYCLER_VIEW);
		recyclerView.setLayoutManager(this.createLayoutManager());
		recyclerView.setAdapter(this.createAdapter(this.list));

		db.close();
	} else {
		Intent intent = new Intent(BroadcastReceiver.VERIFY_DB_ACTION);
		intent.putExtra(BroadcastReceiver.REDIRECT, true);
		intent.putExtra(SHOW, true);
		this.sendBroadcast(intent);
		this.finish();
	}

	Log.i(TAG, "onResume");
}

@Override
protected void onStop() {
	super.onStop();

	this.unregisterReceiver(this.getBroadcastReceiver());

	Log.i(TAG, "onStop");

	this.finish();
}

@Override
protected void onDestroy() {
	super.onDestroy();

	try {
		this.unregisterReceiver(this.getBroadcastReceiver());
	} catch (IllegalArgumentException ignored) {
	}

	Log.i(TAG, "onDestroy");

	this.finish();
}

private void register() {
	IntentFilter filter = new IntentFilter(BroadcastReceiver.VERIFY_DB_ACTION);
	filter.addCategory(Intent.CATEGORY_DEFAULT);

	this.registerReceiver(this.getBroadcastReceiver(), filter);
}

@Override
public void onClick(View v) {
	if (v == this.delete) {
		Helper db = Database.INSTANCE.createHelper(this);

		TableManager<T> manager = this.createManager();
		manager.delete(db.writeProxy(), manager.init());

		db.close();

		this.startActivity(new Intent(this, ResourcesListActivity.class));
		this.finish();
	}
}

@Override
public void checkList() {
	if (this.list.size() == 0) {
		Toast.makeText(this, NO_ITEMS_MSG, Toast.LENGTH_LONG)
			.show();
		finish();
	}
}
}
