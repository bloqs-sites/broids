package pt.epcc.alunos.al220007.bloqs.models.client;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class ClientIntentService extends IntentService<Client> {
@Override
public ModelManager<Client> createManager() {
	return new ClientManager();
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return ClientActivity.class;
}

}
