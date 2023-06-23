package pt.epcc.alunos.al220007.bloqs.list;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.core.Model;

abstract public class Activity<T extends Model> extends AppCompatActivity {
private static final int LAYOUT = R.layout.activity_models_list;
private static final int RECYCLER_VIEW = R.id.list;

protected abstract @NonNull TableManager<T> createManager();

protected abstract @NonNull Adapter<T> createAdapter(List<T> list);

protected abstract @NonNull RecyclerView.LayoutManager createLayoutManager();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(Activity.LAYOUT);

	Helper db = new Helper(this, Database.INSTANCE);

	RecyclerView recyclerView = this.findViewById(Activity.RECYCLER_VIEW);
	Button btn = this.findViewById(R.id.delete);
	btn.setOnClickListener(v -> {
		this.deleteDatabase(Database.INSTANCE.getName());
		this.finish();
	});

	Iterable<T> items = this.createManager().read(db.readProxy(), new HashMap<>());
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
