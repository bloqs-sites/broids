package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.models.Model;

abstract public class Adapter<T extends Model, U extends ViewHolder<T>> extends RecyclerView.Adapter<U> {
private final Context ctx;
private final List<T> list;

public Adapter(Context ctx, List<T> list) {
	this.ctx = ctx;
	this.list = list;
}

public abstract int getLayout();

public abstract U createViewHolder(Context ctx, @NonNull View v);

@NonNull
@Override
public U onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
	return this.createViewHolder(this.ctx, LayoutInflater.from(parent.getContext()).inflate(this.getLayout(), parent, false));
}

@Override
public void onBindViewHolder(@NonNull U holder, int position) {
	holder.setModel(this.list.get(position));
}

@Override
public int getItemCount() {
	return this.list.size();
}
}
