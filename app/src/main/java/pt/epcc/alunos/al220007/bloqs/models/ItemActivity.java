package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.list.Activity;

public class ItemActivity extends Activity<Item, ItemManager, ItemAdapter> {
@NonNull
@Override
public ItemManager createManager() {
	return new ItemManager();
}

@NonNull
@Override
public ItemAdapter createAdapter(List<Item> list) {
	return new ItemAdapter(this, list);
}

@NonNull
@Override
public RecyclerView.LayoutManager createLayoutManager() {
	return new LinearLayoutManager(this);
}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {
	super.onPointerCaptureChanged(hasCapture);
}
}
