package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.db.Helper;

abstract public class ModelManager<T extends Model> extends TableManager<T> {
	abstract public @NonNull String category();
	abstract public T fromJson(JSONObject json);
	abstract public void populateFromJson(Helper db, JSONArray jsonArray);
}
