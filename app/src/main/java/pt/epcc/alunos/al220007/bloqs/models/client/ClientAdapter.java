package pt.epcc.alunos.al220007.bloqs.models.client;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.ActivityProxy;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class ClientAdapter extends Adapter<Client> {
public static final int LAYOUT = R.layout.client_card;

public ClientAdapter(Context ctx, List<Client> list, ActivityProxy proxy) {
	super(ctx, list, proxy);
}

@Override
public int getLayout() {
	return LAYOUT;
}

@Override
public ViewHolder<Client> createViewHolder(Context ctx, @NonNull View v) {
	return new ClientViewHolder(ctx, v);
}
}
