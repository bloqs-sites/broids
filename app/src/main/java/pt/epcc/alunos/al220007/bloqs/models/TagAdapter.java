package pt.epcc.alunos.al220007.bloqs.models;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class TagAdapter extends Adapter<Tag, TagAdapter.TagViewHolder> {
public static final int LAYOUT = R.layout.item_card;

public TagAdapter(Context ctx, List<Tag> list) {
	super(ctx, list);
}

@Override
public int getLayout() {
	return LAYOUT;
}

@Override
public TagViewHolder createViewHolder(Context ctx, @NonNull View v) {
	return new TagViewHolder(ctx, v);
}

public static class TagViewHolder extends ViewHolder<Tag> {

	public TagViewHolder(Context ctx, @NonNull View itemView) {
		super(ctx, itemView);
	}

	@Override
	protected void manageItemView() {
		Tag i = this.getModel();
		this.view.findViewById(Math.toIntExact(i.getId()));
	}
}
}
