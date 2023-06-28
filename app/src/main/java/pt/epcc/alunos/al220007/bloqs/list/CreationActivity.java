package pt.epcc.alunos.al220007.bloqs.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.Map;

import jvmdbhelper.model.Model;
import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.db.Database;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

abstract public class CreationActivity<T extends Model> extends AppCompatActivity implements View.OnClickListener {
private static final int CREATE_BTN_ID = R.id.create;
private static final int LAYOUT = R.layout.activity_creation;
private final TableManager<T> manager = this.createManager();
private Button create;
private ViewStub form;
private T model;

protected abstract @NonNull TableManager<T> createManager();

protected abstract int formLayout();

protected abstract @Nullable Map<String, Object> createWhereClause(Intent intent);

protected abstract void findViews(@NonNull View view, @Nullable T o);

protected abstract void manageModel(@NonNull T o);

abstract protected @NonNull Class<?> createRedirectClass();

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(LAYOUT);

	this.create = this.findViewById(CREATE_BTN_ID);
	this.create.setOnClickListener(this);

	this.form = this.findViewById(R.id.form);
	this.form.setLayoutResource(this.formLayout());
}


@Override
protected void onResume() {
	super.onResume();

	Map<String, Object> where = this.createWhereClause(this.getIntent());
	if (where != null) {
		this.create.setText(R.string.edit);
		Helper db = Database.INSTANCE.createHelper(this);
		Iterator<T> iterator = this.manager.read(db.readProxy(), where).iterator();
		if (iterator.hasNext()) {
			this.model = iterator.next();
		} else {
			Toast.makeText(
				this,
				"Could not find model with specified ID",
				Toast.LENGTH_SHORT
			).show();
			finish();
		}
		db.close();
	} else {
		this.create.setText(R.string.create);
	}

	this.findViews(this.form.inflate(), this.model);
}

@Override
public void onClick(View v) {
	if (v == this.create) {
		Helper db = Database.INSTANCE.createHelper(this);
		if (this.model != null) {
			this.manageModel(this.model);
			this.manager.update(db.writeProxy(), this.model);
		} else {
			T o = this.manager.init();
			this.manageModel(o);
			this.manager.create(db.writeProxy(), o);
		}
		db.close();
		this.startActivity(new Intent(this, this.createRedirectClass()));
		finish();
	}
}
}