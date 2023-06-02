package pt.epcc.alunos.al220007.bloqs.list;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.async.HTTPGETJSON;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.Model;

abstract public class Activity<T extends Model, M extends TableManager<T>, U extends Adapter<T, ? extends ViewHolder<T>>> extends AppCompatActivity {
private static final int LAYOUT = R.layout.activity_models_list;
private static final int RECYCLER_VIEW = R.id.list;

private Future<String> future;

protected abstract @NonNull M createManager();

protected abstract @NonNull U createAdapter(List<T> list);

protected abstract @NonNull URL createResource() throws MalformedURLException;

protected abstract @NonNull RecyclerView.LayoutManager createLayoutManager();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(Activity.LAYOUT);

	this.pull();

	Helper db = new Helper(this, Database.INSTANCE);

	RecyclerView recyclerView = this.findViewById(Activity.RECYCLER_VIEW);
	Button btn = this.findViewById(R.id.delete);
	btn.setOnClickListener(v -> {
		this.deleteDatabase(Database.INSTANCE.getName());
		this.finish();
	});

	Iterable<T> items = (Iterable<T>) this.createManager().read(db.readProxy(), new HashMap<>());
	List<T> list = new ArrayList<>();
	for (T i :
		items) {
		list.add(i);
	}

	recyclerView.setLayoutManager(this.createLayoutManager());
	recyclerView.setAdapter(this.createAdapter(list));

	db.close();

	this.then();
}

private void pull() {
	try {
		this.future = Executors.newFixedThreadPool(1).submit(new HTTPGETJSON(this.createResource()));
	} catch (MalformedURLException e) {
		Log.e("MalformedURLException", String.valueOf(e));
	}
}

private void then() {
	if (this.future == null) {
		return;
	}

	try {
		this.consumeFuture(this.future.get());
	} catch (ExecutionException e) {
		Log.e("ExecutionException", String.valueOf(e));
	} catch (InterruptedException e) {
		Log.e("InterruptedException", String.valueOf(e));
	}
}

protected abstract void consumeFuture(String buf);

}