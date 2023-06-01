package pt.epcc.alunos.al220007.bloqs.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jvmdbhelper.model.Model;
import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

abstract public class Activity<T extends Model, M extends TableManager<T>, U extends Adapter<T, ? extends ViewHolder<T>>> extends AppCompatActivity {
public static final int LAYOUT = R.layout.activity_models_list;
public static final int RECYCLER_VIEW = R.id.list;

public abstract @NonNull M createManager();

public abstract @NonNull U createAdapter(List<T> list);

public abstract @NonNull RecyclerView.LayoutManager createLayoutManager();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(Activity.LAYOUT);

	Helper db = new Helper(this, Database.INSTANCE);

	RecyclerView recyclerView = this.findViewById(Activity.RECYCLER_VIEW);

	Iterable<T> items = (Iterable<T>) this.createManager().read(db.proxy(db.getReadableDatabase()), new HashMap<>());
	List<T> list = new ArrayList<>();
	for (T i :
		items) {
		list.add(i);
	}

	recyclerView.setLayoutManager(this.createLayoutManager());
	recyclerView.setAdapter(this.createAdapter(list));

	db.close();
}
}