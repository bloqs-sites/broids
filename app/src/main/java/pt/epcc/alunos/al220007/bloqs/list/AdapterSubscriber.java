package pt.epcc.alunos.al220007.bloqs.list;

import pt.epcc.alunos.al220007.bloqs.core.Model;

public interface AdapterSubscriber<T extends Model> {
void setPublisherProxy(AdapterProxy<T> proxy);

void update(AdapterPublisherEvent event);
}
