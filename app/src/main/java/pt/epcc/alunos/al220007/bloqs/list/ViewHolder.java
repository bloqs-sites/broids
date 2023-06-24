package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.epcc.alunos.al220007.bloqs.core.Model;

public abstract class ViewHolder<T extends Model> extends RecyclerView.ViewHolder implements AdapterSubscriber<T> {
private final Context ctx;
private final String TAG = this.getClass().getSimpleName();
private AdapterProxy<T> proxy;
private T model;

public ViewHolder(Context ctx, @NonNull View itemView) {
	super(itemView);
	this.ctx = ctx;
	Log.i(TAG, "ViewHolder");
}

protected abstract void manageItemView();

protected T getModel() {
	return this.model;
}

public void setModel(T model) {
	this.model = model;
	this.manageItemView();
}

protected Context getContext() {
	return this.ctx;
}

protected AdapterProxy<T> getPublisherProxy() {
	return this.proxy;
}

@Override
public void setPublisherProxy(AdapterProxy<T> proxy) {
	this.proxy = proxy;
}
}
