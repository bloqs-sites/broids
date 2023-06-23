package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

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
@SuppressWarnings("unchecked")
public Class<Activity<User>> createRedirectActivityClass() {
	return (Class<Activity<User>>) (Class<?>) UserActivity.class;
}
}
