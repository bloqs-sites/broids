package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import jvmdbhelper.db_defenitions.Column;
import jvmdbhelper.db_defenitions.Table;
import jvmdbhelper.db_defenitions.Type;
import jvmdbhelper.model.TableManager;

public class ItemManager extends TableManager<Item> {
public static final String NAME = "items";
public static final String COL_ID = "_id";
public static final String COL_TITLE = "title";
public static final String COL_URI = "uri";
public static final String COL_STATE = "state";
public static final String COL_CREATION_DATE = "creation_date";

@NonNull
@Override
public Item init() {
	return new Item();
}

@Override
protected void manageTable(@NonNull Table table) {
	Column title = new Column(ItemManager.COL_TITLE, Type.TXT);
	title.setNullable(false);

	Column uri = new Column(ItemManager.COL_URI, Type.TXT);
	uri.setNullable(true);

	Column state = new Column(ItemManager.COL_STATE, Type.INT);
	state.setNullable(false);

	Column creation_date = new Column(ItemManager.COL_CREATION_DATE, Type.DATETIME);
	creation_date.setNullable(true);

	table.addPK(ItemManager.COL_ID, true).addColumn(title, uri, state, creation_date);
}

@NonNull
@Override
public String getName() {
	return ItemManager.NAME;
}
}
