package pt.epcc.alunos.al220007.bloqs.models;

import pt.epcc.alunos.al220007.bloqs.list.IntentService;
import pt.epcc.alunos.al220007.bloqs.models.core.ModelManager;

public class UserIntentService extends IntentService<User> {
@Override
public ModelManager<User> createManager() {
	return new UserManager();
}

}
