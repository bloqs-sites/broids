package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pt.epcc.alunos.al220007.bloqs.list.Activity;

final public class TagActivity extends Activity<Tag, Tag.Companion, TagAdapter> {
private static final int COUNT = 3;

@NonNull
@Override
protected Tag.Companion createManager() {
	return Tag.Companion;
}

@NonNull
@Override
protected TagAdapter createAdapter(List<Tag> list) {
	return new TagAdapter(this, list);
}

@NonNull
@Override
protected URL createResource() throws MalformedURLException {
	return new URL("");
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new GridLayoutManager(this, TagActivity.COUNT);
}

@Override
protected void consumeFuture(String buf) {

}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {
	super.onPointerCaptureChanged(hasCapture);
}
}
