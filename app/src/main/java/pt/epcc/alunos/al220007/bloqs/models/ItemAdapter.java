package pt.epcc.alunos.al220007.bloqs.models;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class ItemAdapter extends Adapter<Item, ItemAdapter.ItemViewHolder> {
public static final int LAYOUT = R.layout.item_card;
public static final int ID_ID = R.id.id;
public static final int ID_TITLE = R.id.title;

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

public static class ItemViewHolder extends ViewHolder<Item> implements View.OnClickListener {
	public ItemViewHolder(Context ctx, @NonNull View itemView) {
		super(ctx, itemView);
	}

	@Override
	protected void manageItemView() {
		this.view.setOnClickListener(this);

		Item i = this.getModel();

		TextView id = this.view.findViewById(ID_ID);
		id.setText(String.valueOf(i.getId()));
		TextView txt = this.view.findViewById(ID_TITLE);
		txt.setText(i.title);
	}

	@Override
	public void onClick(View v) {
		if (v == this.view) {
			this.ctx.startActivity(new Intent(this.ctx, ItemInfoActivity.class).putExtra(ItemManager.COL_ID, this.getModel().getId()));
		}
	}
}
}
