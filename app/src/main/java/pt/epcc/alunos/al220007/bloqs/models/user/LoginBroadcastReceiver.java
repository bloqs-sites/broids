package pt.epcc.alunos.al220007.bloqs.models.user;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.LoginActivity;
import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class LoginBroadcastReceiver extends BroadcastReceiver<User> {
@NonNull
@Override
public ModelManager<User> createManager() {
	return new UserManager();
}

@NonNull
@Override
@SuppressWarnings("unchecked")
public Class<IntentService<User>> createServiceClass() {
	return (Class<pt.epcc.alunos.al220007.bloqs.list.IntentService<User>>) (Class<?>) LoginIntentService.class;
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return LoginActivity.class;
}
}
