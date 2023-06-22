package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import jvmdbhelper.db_defenitions.Column;
import jvmdbhelper.db_defenitions.Table;
import jvmdbhelper.db_defenitions.Type;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

public class UserManager extends ModelManager<User> {
public static final String NAME = "users";
public static final String COL_ID = "_id";
public static final String COL_USER = "user";
public static final String COL_PASS = "pass";

@NonNull
@Override
public String getName() {
	return NAME;
}

@NonNull
@Override
public User init() {
	return new User();
}

@Override
protected void manageTable(@NonNull Table table) {
	Column user = new Column(COL_USER, Type.TXT);
	user.setNullable(false);
	user.setUnique(true);

	Column pass = new Column(COL_PASS, Type.TXT);
	pass.setNullable(false);

	table.addPK(COL_ID, true).addColumn(user, pass);
}

@Override
@NonNull
public String category() {
	return "users";
}

@Override
public User fromJson(JSONObject json) {
	return null;
}

@Override
public void populateFromJson(Helper db, JSONArray jsonArray) {
	Iterable<User> iterator = new JSONArrayIterator<>(jsonArray, this);
//	for (Iterator<User> it = iterator; it.hasNext(); ) {
//		User u = it.next();
	for (User u:
	     iterator) {
		this.create(db.writeHelper().getProxy(), u);
	}
}
}
