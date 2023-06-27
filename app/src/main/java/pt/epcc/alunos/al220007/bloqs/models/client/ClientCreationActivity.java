package pt.epcc.alunos.al220007.bloqs.models.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

public class ClientCreationActivity extends AppCompatActivity implements View.OnClickListener {
private static final int CREATE_BTN_ID = R.id.create;
private static final int NAME_ID = R.id.name;
private static final int AGE_ID = R.id.age;
private static final int LAYOUT = R.layout.activity_client_creation;

private TextView name, age;
private Button create;

private Client model;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();
}

private void findViews() {
	this.name = this.findViewById(NAME_ID);
	this.age = this.findViewById(AGE_ID);
	this.create = this.findViewById(CREATE_BTN_ID);
	this.create.setOnClickListener(this);


	long id = this.getIntent().getLongExtra("ID", Long.MAX_VALUE);
	if (id != Long.MAX_VALUE) {
		ModelManager<Client> manager = new ClientManager();
		Helper db = Database.INSTANCE.createHelper(this);
		Map<String, Object> where = new HashMap<>();
		where.put(ClientManager.COL_ID, id);
		Iterator<Client> iterator = manager.read(db.readProxy(), where).iterator();
		if (iterator.hasNext()) {
			this.model = iterator.next();
			this.name.setText(this.model.getName());
			this.age.setText(String.valueOf(this.model.getAge()));
		}
		db.close();
	}
}

@Override
public void onClick(View v) {
	if (v == this.create) {
		ModelManager<Client> manager = new ClientManager();
		Helper db = Database.INSTANCE.createHelper(this);
		if (this.model != null) {
			this.model.setName(String.valueOf(this.name.getText()));
			this.model.setAge(Byte.parseByte(this.age.getText().toString()));
			manager.update(db.writeProxy(), this.model);
		} else {
			Client o = new Client();
			o.setName(String.valueOf(this.name.getText()));
			o.setAge(Byte.parseByte(this.age.getText().toString()));
			manager.create(db.writeProxy(), o);
		}
		db.close();
		this.startActivity(new Intent(this, ClientActivity.class));
		finish();
	}
}
}