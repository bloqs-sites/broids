package pt.epcc.alunos.al220007.bloqs.models.user;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.AdapterPublisherEvent;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class UserViewHolder extends ViewHolder<User> {
public UserViewHolder(Context ctx, @NonNull View itemView) {
	super(ctx, itemView);
}

@Override
protected void manageItemView() {
	User o = this.getModel();
	TextView id = this.itemView.findViewById(R.id.user_id);
	id.setText(String.valueOf(o.getId()));

	TextView user = this.itemView.findViewById(R.id.user_user);
	user.setText(o.getUser());

	Button delete = this.itemView.findViewById(R.id.delete);
	delete.setOnClickListener(v -> this.getPublisherProxy().delete(this, new UserManager()));
}

@Override
public void update(AdapterPublisherEvent event) {

}
}
