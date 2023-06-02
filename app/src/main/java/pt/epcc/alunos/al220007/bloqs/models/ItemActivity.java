package pt.epcc.alunos.al220007.bloqs.models;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pt.epcc.alunos.al220007.bloqs.list.Activity;

final public class ItemActivity extends Activity<Item, ItemManager, ItemAdapter> {
@NonNull
@Override
protected ItemManager createManager() {
	return new ItemManager();
}

@NonNull
@Override
protected ItemAdapter createAdapter(List<Item> list) {
	return new ItemAdapter(this, list);
}

@NonNull
@Override
protected URL createResource() throws MalformedURLException {
	return new URL("https://bloqs.torres-dev.workers.dev/cnf");
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new LinearLayoutManager(this);
}

@Override
protected void consumeFuture(String buf) {
	Toast.makeText(this, buf, Toast.LENGTH_SHORT).show();
}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {
	super.onPointerCaptureChanged(hasCapture);
}
}
