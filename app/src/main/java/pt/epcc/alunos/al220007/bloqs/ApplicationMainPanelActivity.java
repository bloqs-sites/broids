package pt.epcc.alunos.al220007.bloqs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import pt.epcc.alunos.al220007.bloqs.models.real_estate.RealEstateActivity;

public class ApplicationMainPanelActivity extends AppCompatActivity implements View.OnClickListener {
private static final String TAG = "MainPanelActivity";

private static final int REAL_ESTATE_ACT_BTN_ID = R.id.real_estates;
private static final int APPLICATION_MANAGEMENT_ACT_BTN_ID = R.id.manage;

private static final int LAYOUT = R.layout.activity_application_main_panel;

private Button real_estate, manage;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.findViews();

	Log.i(TAG, "onCreate");
}

private void findViews() {
	this.real_estate = this.findViewById(REAL_ESTATE_ACT_BTN_ID);
	this.real_estate.setOnClickListener(this);
	this.manage = this.findViewById(APPLICATION_MANAGEMENT_ACT_BTN_ID);
	this.manage.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	Class<?> cls = null;

	if (v == this.real_estate) {
		cls = RealEstateActivity.class;
	}

	if (v == this.manage) {
		cls = ApplicationManagementActivity.class;
	}

	if (cls != null) {
		this.startActivity(new Intent(this, cls));
	}
}
}
