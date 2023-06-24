package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.epcc.alunos.al220007.bloqs.models.UserActivity;

public class ResourcesListActivity extends AppCompatActivity implements View.OnClickListener {

private static final int USER_ACT_BTN_ID = R.id.reset_db_btn;

private static final int LAYOUT = R.layout.activity_main;

private Button users;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();
}

private void findViews() {
	this.users = this.findViewById(USER_ACT_BTN_ID);
	this.users.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	Class<?> cls = null;

	if (v == this.users) {
		cls = UserActivity.class;
	}

	if (cls != null) {
		this.startActivity(new Intent(this, cls));
	}
}
}
