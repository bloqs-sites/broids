package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;

public class RealEstateActivity extends Activity<RealEstate> {
private static final int GRID_COLS = 3;
private final BroadcastReceiver<RealEstate> receiver = new RealEstateBroadcastReceiver();

@NonNull
@Override
protected TableManager<RealEstate> createManager() {
	return new RealEstateManager();
}

@NonNull
@Override
protected Adapter<RealEstate> createAdapter(List<RealEstate> list) {
	return new RealEstateAdapter(this, list, this);
}

@NonNull
@Override
protected RecyclerView.LayoutManager createLayoutManager() {
	return new GridLayoutManager(this, GRID_COLS);
}

@NonNull
@Override
protected BroadcastReceiver<RealEstate> getBroadcastReceiver() {
	return this.receiver;
}
}