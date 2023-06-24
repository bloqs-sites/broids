package pt.epcc.alunos.al220007.bloqs.models.client;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class ClientBroadcastReceiver extends BroadcastReceiver<Client> {
@NonNull
@Override
public ModelManager<Client> createManager() {
	return new ClientManager();
}

@NonNull
@Override
@SuppressWarnings("unchecked")
public Class<IntentService<Client>> createServiceClass() {
	return (Class<IntentService<Client>>) (Class<?>) ClientIntentService.class;
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return ClientActivity.class;
}
}
