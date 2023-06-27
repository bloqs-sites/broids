package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.list.Activity;
import pt.epcc.alunos.al220007.bloqs.list.Adapter;
import pt.epcc.alunos.al220007.bloqs.list.BroadcastReceiver;

public class RealEstateManagementActivity extends Activity<RealEstate> {
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
	return new LinearLayoutManager(this);
}

@NonNull
@Override
protected BroadcastReceiver<RealEstate> getBroadcastReceiver() {
	return this.receiver;
}

@Override
protected View.OnClickListener onCreate() {
	return v -> this.startActivity(new Intent(this, RealEstateCreationActivity.class));
}

@Override
protected boolean resetTableButton() {
	return true;
}
}
