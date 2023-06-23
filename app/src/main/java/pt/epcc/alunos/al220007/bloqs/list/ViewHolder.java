package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.epcc.alunos.al220007.bloqs.models.core.Model;

public abstract class ViewHolder<T extends Model> extends RecyclerView.ViewHolder {
protected final View view;
protected final Context ctx;
private T model;

public ViewHolder(Context ctx, @NonNull View itemView) {
	super(itemView);
	this.ctx = ctx;
	this.view = itemView;
}

protected abstract void manageItemView();

protected T getModel() {
	return this.model;
}

public void setModel(T model) {
	this.model = model;
	this.manageItemView();
}
}
