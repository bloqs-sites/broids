package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.models.user.LoginBroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.models.user.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private static final String TAG = "MainActivity";

private static final int RESET_DB_BTN_ID = R.id.reset_db_btn;
private static final int LAYOUT = R.layout.activity_main;

private final BroadcastReceiver<User> receiver = new LoginBroadcastReceiver();
private Button resetDatabaseButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.register();

	this.findViews();

	Log.i(TAG, "onCreate");
}

private void findViews() {
	this.resetDatabaseButton = this.findViewById(RESET_DB_BTN_ID);
	this.resetDatabaseButton.setOnClickListener(this);
}

private void register() {
	IntentFilter filter = new IntentFilter(BroadcastReceiver.VERIFY_DB_ACTION);
	filter.addCategory(Intent.CATEGORY_DEFAULT);

	this.registerReceiver(this.receiver, filter);
}

@Override
protected void onResume() {
	super.onResume();

	this.register();

	Intent intent = new Intent(BroadcastReceiver.VERIFY_DB_ACTION);
	intent.putExtra(BroadcastReceiver.REDIRECT, true);
	this.sendBroadcast(intent);

	Log.i(TAG, "onResume");
}

@Override
protected void onStop() {
	super.onStop();

	this.unregisterReceiver(this.receiver);

	Log.i(TAG, "onStop");

	this.finish();
}

@Override
protected void onDestroy() {
	super.onDestroy();

	try {
		this.unregisterReceiver(this.receiver);
	} catch (IllegalArgumentException ignored) {
	}

	Log.i(TAG, "onDestroy");

	this.finish();
}

@Override
public void onClick(View v) {
	if (v == this.resetDatabaseButton) {
		deleteDatabase(Database.INSTANCE.getName());
		this.finish();
	}
}
}
