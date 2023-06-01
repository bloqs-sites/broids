package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.list.Activity;

public class TagActivity extends Activity<Tag, Tag.Companion, TagAdapter> {
private static final int COUNT = 3;

@NonNull
@Override
public Tag.Companion createManager() {
	return Tag.Companion;
}

@NonNull
@Override
public TagAdapter createAdapter(List<Tag> list) {
	return new TagAdapter(this, list);
}

@NonNull
@Override
public RecyclerView.LayoutManager createLayoutManager() {
	return new GridLayoutManager(this, TagActivity.COUNT);
}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {
	super.onPointerCaptureChanged(hasCapture);
}
}
