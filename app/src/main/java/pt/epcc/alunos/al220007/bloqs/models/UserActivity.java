package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;

public class UserActivity extends Activity<User> {
@NonNull
@Override
protected TableManager<User> createManager() {
	return new UserManager();
}

@NonNull
@Override
protected Adapter<User> createAdapter(List<User> list) {
	return new UserAdapter(this, list);
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new LinearLayoutManager(this);
}
}
