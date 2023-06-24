package pt.epcc.alunos.al220007.bloqs.models.user;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class UserIntentService extends IntentService<User> {
@Override
public ModelManager<User> createManager() {
	return new UserManager();
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return UserActivity.class;
}

}
