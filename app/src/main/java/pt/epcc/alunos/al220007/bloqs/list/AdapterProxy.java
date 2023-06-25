package pt.epcc.alunos.al220007.bloqs.list;

import jvmdbhelper.model.Model;
import jvmdbhelper.model.TableManager;

public interface AdapterProxy<T extends Model> {
void delete(ViewHolder<T> holder, TableManager<T> manager);
}
