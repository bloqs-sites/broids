package pt.epcc.alunos.al220007.bloqs.models.client;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.AdapterPublisherEvent;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class ClientViewHolder extends ViewHolder<Client> {
public ClientViewHolder(Context ctx, @NonNull View itemView) {
	super(ctx, itemView);
}

@Override
protected void manageItemView() {
	Client o = this.getModel();

	TextView id = this.itemView.findViewById(R.id.client_id);
	id.setText(String.valueOf(o.getId()));

	TextView name = this.itemView.findViewById(R.id.client_name);
	name.setText(o.getName());

	Button delete = this.itemView.findViewById(R.id.delete);
	delete.setOnClickListener(v -> this.getPublisherProxy().delete(this, new ClientManager()));
}

@Override
public void update(AdapterPublisherEvent event) {

}
}