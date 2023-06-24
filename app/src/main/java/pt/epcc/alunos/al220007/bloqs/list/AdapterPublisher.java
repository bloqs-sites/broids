package pt.epcc.alunos.al220007.bloqs.list;

import android.view.View;

import java.util.HashSet;
import java.util.Set;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.core.Model;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

public class AdapterPublisher<T extends Model> implements AdapterProxy<T> {
final Set<ViewHolder<T>> list;
final Adapter<T> adapter;

public AdapterPublisher(Adapter<T> adapter) {
	this.list = new HashSet<>();
	this.adapter = adapter;
}

public void register(ViewHolder<T> subscriber) {
	subscriber.setPublisherProxy(this);
	this.list.add(subscriber);
}

public void unregister(ViewHolder<T> subscriber) {
	this.list.remove(subscriber);
}

public void update(AdapterPublisherEvent event) {
	for (ViewHolder<T> listener :
		this.list) {
		listener.update(event);
	}
}

@Override
public void delete(ViewHolder<T> holder, TableManager<T> manager) {
	this.adapter.getList().remove(holder.getModel());
	int i = holder.getAdapterPosition();
	this.adapter.notifyItemRemoved(i);
	this.adapter.notifyItemRangeChanged(i, this.adapter.getList().size());
	holder.itemView.setVisibility(View.GONE);

	Helper db = Database.INSTANCE.createHelper(holder.getContext());
	manager.delete(db.writeProxy(), holder.getModel());
	db.close();

	this.update(AdapterPublisherEvent.DELETE);

	this.adapter.proxy.checkList();
}
}
