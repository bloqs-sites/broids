package pt.epcc.alunos.al220007.bloqs.list;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.core.Model;

public interface AdapterProxy<T extends Model> {
void delete(ViewHolder<T> holder, TableManager<T> manager);
}
