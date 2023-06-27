package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.NetworkImageView;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.core.async.Volley;
import pt.epcc.alunos.al220007.bloqs.list.AdapterPublisherEvent;
import pt.epcc.alunos.al220007.bloqs.list.ViewHolder;

public class RealEstateViewHolder extends ViewHolder<RealEstate> {
public RealEstateViewHolder(Context ctx, @NonNull View itemView) {
	super(ctx, itemView);
}

@Override
protected void manageItemView() {
	RealEstate o = this.getModel();
	NetworkImageView image = this.itemView.findViewById(R.id.real_estate_image);
	image.setImageUrl(o.getPhoto().toString(), Volley.getInstance(this::getContext).getImageLoader());
	TextView description = this.itemView.findViewById(R.id.real_estate_description);
	description.setText(o.getDescription());
	TextView typology = this.itemView.findViewById(R.id.real_estate_typology);
	typology.setText(String.format("t%s", o.getTypology()));
	TextView location = this.itemView.findViewById(R.id.real_estate_location);
	location.setText(o.getLocation());
	ListView characteristics = this.itemView.findViewById(R.id.real_estate_characteristics);
	characteristics.setAdapter(new ArrayAdapter<>(this.getContext(), R.layout.real_estate_card, o.getCharacteristics()));
}

@Override
public void update(AdapterPublisherEvent event) {
}
}
