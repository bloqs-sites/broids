package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.epcc.alunos.al220007.bloqs.models.client.ClientActivity;
import pt.epcc.alunos.al220007.bloqs.models.user.UserActivity;

public class ResourcesListActivity extends AppCompatActivity implements View.OnClickListener {
private static final String TAG = "ResourcesListActivity";

private static final int USER_ACT_BTN_ID = R.id.users;
private static final int CLIENT_ACT_BTN_ID = R.id.clients;

private static final int LAYOUT = R.layout.activity_resources_list;

private Button users, clients;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();

	Log.i(TAG, "onCreate");
}

private void findViews() {
	this.users = this.findViewById(USER_ACT_BTN_ID);
	this.users.setOnClickListener(this);
	this.clients = this.findViewById(CLIENT_ACT_BTN_ID);
	this.clients.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	Class<?> cls = null;

	if (v == this.users) {
		cls = UserActivity.class;
	}

	if (v == this.clients) {
		cls = ClientActivity.class;
	}

	if (cls != null) {
		this.startActivity(new Intent(this, cls));
	}
}
}
