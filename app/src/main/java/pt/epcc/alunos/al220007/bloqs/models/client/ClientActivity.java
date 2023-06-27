package pt.epcc.alunos.al220007.bloqs.models.client;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;

public class ClientActivity extends Activity<Client> {

private final BroadcastReceiver<Client> receiver = new ClientBroadcastReceiver();

@NonNull
@Override
protected TableManager<Client> createManager() {
	return new ClientManager();
}

@NonNull
@Override
protected Adapter<Client> createAdapter(List<Client> list) {
	return new ClientAdapter(this, list, this);
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new LinearLayoutManager(this);
}

@NonNull
@Override
protected BroadcastReceiver<Client> getBroadcastReceiver() {
	return this.receiver;
}

@Override
protected View.OnClickListener onCreate() {
	return v -> this.startActivity(new Intent(this, ClientCreationActivity.class));
}

@Override
protected boolean resetTableButton() {
	return true;
}
}
