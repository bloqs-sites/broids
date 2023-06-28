package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.list.ActivityProxy;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class RealEstateManagerAdapter extends Adapter<RealEstate> {
public static final int LAYOUT = R.layout.real_estate_manager_card;

public RealEstateManagerAdapter(Context ctx, List<RealEstate> list, ActivityProxy proxy) {
	super(ctx, list, proxy);
}


@Override
public int getLayout() {
	return LAYOUT;
}

@Override
public ViewHolder<RealEstate> createViewHolder(Context ctx, @NonNull View v) {
	return new RealEstateCreationViewHolder(ctx, v);
}
}
