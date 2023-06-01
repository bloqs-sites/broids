package pt.epcc.alunos.al220007.bloqs.models;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

public class ItemInfoActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.item_info);

	Helper db = new Helper(this, Database.INSTANCE);

	long id = this.getIntent().getLongExtra(ItemManager.COL_ID, Long.MIN_VALUE);
	if (id == Long.MIN_VALUE) {
		finish();
	}

	Map<String, Object> filter = new HashMap<>();
	filter.put(ItemManager.COL_ID, id);

	Item i = new ItemManager().read(db.readProxy(), filter).iterator().next();

	TextView title = this.findViewById(R.id.title);
	TextView uri = this.findViewById(R.id.uri);
	TextView state = this.findViewById(R.id.state);
	title.setText(i.title);
	uri.setText(i.uri.toString());
	state.setText(String.valueOf(i.state));

	db.close();
}
}