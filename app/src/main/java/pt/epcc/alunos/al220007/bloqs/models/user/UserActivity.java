package pt.epcc.alunos.al220007.bloqs.models.user;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;

public class UserActivity extends Activity<User> {
private final BroadcastReceiver<User> receiver = new UserBroadcastReceiver();

@NonNull
@Override
protected TableManager<User> createManager() {
	return new UserManager();
}

@NonNull
@Override
protected Adapter<User> createAdapter(List<User> list) {
	return new UserAdapter(this, list, this);
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new LinearLayoutManager(this);
}

@NonNull
@Override
protected BroadcastReceiver<User> getBroadcastReceiver() {
	return this.receiver;
}

@Override
protected View.OnClickListener onCreate() {
	return null;
}

@Override
protected boolean resetTableButton() {
	return true;
}
}
