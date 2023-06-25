package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class RealEstateIntentService extends IntentService<RealEstate> {
@Override
public ModelManager<RealEstate> createManager() {
	return new RealEstateManager();
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return RealEstateActivity.class;
}

}
