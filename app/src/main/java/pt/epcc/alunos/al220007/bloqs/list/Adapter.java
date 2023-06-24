package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.core.Model;

abstract public class Adapter<T extends Model> extends RecyclerView.Adapter<ViewHolder<T>> {
private final String TAG = this.getClass().getSimpleName();
private final Context ctx;

private final List<T> list;

private final AdapterPublisher<T> publisher;

public final ActivityProxy proxy;

public Adapter(Context ctx, List<T> list, ActivityProxy proxy) {
	this.ctx = ctx;
	this.list = list;
	this.publisher = new AdapterPublisher<>(this);
	this.proxy = proxy;
	Log.i(TAG, "Adapter");
}

public abstract int getLayout();

public abstract ViewHolder<T> createViewHolder(Context ctx, @NonNull View v);

@NonNull
@Override
public ViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
	ViewHolder<T> viewHolder = this.createViewHolder(
		this.ctx,
		LayoutInflater.from(parent.getContext())
			.inflate(this.getLayout(), parent, false)
	);

	this.publisher.register(viewHolder);

	return viewHolder;
}

@Override
public void onBindViewHolder(@NonNull ViewHolder<T> holder, int position) {
	holder.setModel(this.list.get(position));
}

@Override
public int getItemCount() {
	return this.list.size();
}


public List<T> getList() {
	return list;
}
}
