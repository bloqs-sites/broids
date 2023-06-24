package pt.epcc.alunos.al220007.bloqs.models.user;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class UserBroadcastReceiver extends BroadcastReceiver<User> {
@NonNull
@Override
public ModelManager<User> createManager() {
	return new UserManager();
}

@NonNull
@Override
@SuppressWarnings("unchecked")
public Class<IntentService<User>> createServiceClass() {
	return (Class<IntentService<User>>) (Class<?>) UserIntentService.class;
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return UserActivity.class;
}
}
