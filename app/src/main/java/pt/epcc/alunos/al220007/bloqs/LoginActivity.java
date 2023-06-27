package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pt.epcc.alunos.al220007.bloqs.core.Password;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;
import pt.epcc.alunos.al220007.bloqs.models.user.User;
import pt.epcc.alunos.al220007.bloqs.models.user.UserManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private static final String TAG = "LoginActivity";

private static final int LOGIN_BTN_ID = R.id.login;
private static final int USERNAME_ID = R.id.username;
private static final int PASSWORD_ID = R.id.password;
private static final int LAYOUT = R.layout.activity_login;

private TextView username, password;
private Button login;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();
	Log.i(TAG, "onCreate");
}

private void findViews() {
	this.username = this.findViewById(USERNAME_ID);
	this.password = this.findViewById(PASSWORD_ID);
	this.login = this.findViewById(LOGIN_BTN_ID);
	this.login.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	if (v == this.login) {
		this.attemptLogin();
	}
}

private void attemptLogin() {
	Map<String, Object> where = new HashMap<>();
	where.put(UserManager.COL_USER, this.username.getText());

	Helper db = Database.INSTANCE.createHelper(this);

	Iterator<User> iterator = new UserManager().read(db.readProxy(), where).iterator();
	db.close();

	String password = String.valueOf(this.password.getText());

	this.password.setText("");

	User user = iterator.next();

	if (user != null && Password.verify(password, user)) {
		Log.i(TAG, "attemptLogin -> " + user.getUser() + " logged in");
		this.username.setText("");
		this.startActivity(new Intent(this, ApplicationMainPanelActivity.class));
		finish();
		return;
	}

	Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
}
}