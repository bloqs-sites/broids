package pt.epcc.alunos.al220007.bloqs.models.client;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.CreationActivity;

public class ClientCreationActivity extends CreationActivity<Client> {
private static final int NAME_ID = R.id.name;
private static final int AGE_ID = R.id.age;
private TextView name, age;

@NonNull
@Override
protected TableManager<Client> createManager() {
	return new ClientManager();
}

@Override
protected int formLayout() {
	return R.layout.client_form;
}

@Nullable
@Override
protected Map<String, Object> createWhereClause(Intent intent) {
	if (intent == null) {
		return null;
	}

	long id = intent.getLongExtra("ID", Long.MAX_VALUE);
	if (id == Long.MAX_VALUE) {
		return null;
	}

	Map<String, Object> where = new HashMap<>();
	where.put(ClientManager.COL_ID, id);

	return where;
}

@Override
protected void findViews(@NonNull View view, @Nullable Client o) {
	this.name = view.findViewById(NAME_ID);
	this.age = view.findViewById(AGE_ID);

	if (o != null) {
		this.name.setText(o.getName());
		this.age.setText(String.valueOf(o.getAge()));
	}
}

@Override
protected void manageModel(@NonNull Client o) {
	o.setName(String.valueOf(this.name.getText()));
	try {
		o.setAge(Byte.parseByte(this.age.getText().toString()));
	} catch (NumberFormatException e) {
		Toast.makeText(this, "Too old", Toast.LENGTH_SHORT).show();
	}
}

@NonNull
@Override
protected Class<?> createRedirectClass() {
	return ClientActivity.class;
}
}