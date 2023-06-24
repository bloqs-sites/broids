package pt.epcc.alunos.al220007.bloqs.models.user;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.ActivityProxy;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class UserAdapter extends Adapter<User> {
public static final int LAYOUT = R.layout.user_card;

public UserAdapter(Context ctx, List<User> list, ActivityProxy proxy) {
	super(ctx, list, proxy);
}


@Override
public int getLayout() {
	return LAYOUT;
}

@Override
public ViewHolder<User> createViewHolder(Context ctx, @NonNull View v) {
	return new UserViewHolder(ctx, v);
}
}
