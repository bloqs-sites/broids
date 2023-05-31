package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import jvmdbhelper.db_defenitions.Column;
import jvmdbhelper.db_defenitions.Table;
import jvmdbhelper.db_defenitions.Type;
import jvmdbhelper.model.TableManager;

public class ItemManager extends TableManager<Item> {
public static final String NAME = "items";
public static final String COL_ID = "id";
public static final String COL_TITLE = "id";
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
	table.addPK(ItemManager.COL_ID, true).addColumn(
		new Column(ItemManager.COL_TITLE, Type.TXT).nullable(false),
		new Column(ItemManager.COL_URI, Type.TXT).nullable(true),
		new Column(ItemManager.COL_STATE, Type.INT).nullable(false),
		new Column(ItemManager.COL_CREATION_DATE, Type.DATETIME).nullable(true)
	);
}

@NonNull
@Override
public String name() {
	return ItemManager.NAME;
}
}
