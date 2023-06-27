package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.epcc.alunos.al220007.bloqs.models.client.ClientActivity;
import pt.epcc.alunos.al220007.bloqs.models.real_estate.RealEstateManagementActivity;

public class ApplicationManagementActivity extends AppCompatActivity implements View.OnClickListener {
private static final String TAG = "ManagementActivity";
private static final int CLIENT_ACT_BTN_ID = R.id.clients;
private static final int REAL_ESTATE_ACT_BTN_ID = R.id.real_estates;

private static final int LAYOUT = R.layout.activity_application_management;

private Button clients, realEstates;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();

	Log.i(TAG, "onCreate");
}

private void findViews() {
	this.clients = this.findViewById(CLIENT_ACT_BTN_ID);
	this.clients.setOnClickListener(this);
	this.realEstates = this.findViewById(REAL_ESTATE_ACT_BTN_ID);
	this.realEstates.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	Class<?> cls = null;

	if (v == this.clients) {
		cls = ClientActivity.class;
	}

	if (v == this.realEstates) {
		cls = RealEstateManagementActivity.class;
	}

	if (cls != null) {
		this.startActivity(new Intent(this, cls));
	}
}
}