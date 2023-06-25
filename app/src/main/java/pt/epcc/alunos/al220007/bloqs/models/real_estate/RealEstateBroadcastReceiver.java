package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.core.ModelManager;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;
import pt.epcc.alunos.al220007.bloqs.list.IntentService;

public class RealEstateBroadcastReceiver extends BroadcastReceiver<RealEstate> {
@NonNull
@Override
public ModelManager<RealEstate> createManager() {
	return new RealEstateManager();
}

@NonNull
@Override
@SuppressWarnings("unchecked")
public Class<IntentService<RealEstate>> createServiceClass() {
	return (Class<IntentService<RealEstate>>) (Class<?>) RealEstateIntentService.class;
}

@NonNull
@Override
public Class<?> createRedirectClass() {
	return RealEstateActivity.class;
}
}
