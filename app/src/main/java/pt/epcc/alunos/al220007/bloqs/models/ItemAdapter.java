package pt.epcc.alunos.al220007.bloqs.models;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class ItemAdapter extends Adapter<Item, ItemAdapter.ItemViewHolder> {
public static final int LAYOUT = R.layout.model_card;

public ItemAdapter(Context ctx, List<Item> list) {
	super(ctx, list);
}

@Override
public int getLayout() {
	return LAYOUT;
}

@Override
public ItemViewHolder createViewHolder(Context ctx, @NonNull View v) {
	return new ItemViewHolder(ctx, v);
}

public static class ItemViewHolder extends ViewHolder<Item> {
	public ItemViewHolder(Context ctx, @NonNull View itemView) {
		super(ctx, itemView);
	}

	@Override
	protected void manageItemView() {
		Item i = this.getModel();
		this.view.findViewById(Math.toIntExact(i.getId()));
		Toast.makeText(this.ctx, Math.toIntExact(i.getId()), Toast.LENGTH_SHORT).show();
	}
}
}
