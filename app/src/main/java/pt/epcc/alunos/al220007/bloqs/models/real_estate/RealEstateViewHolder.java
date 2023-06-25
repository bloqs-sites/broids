package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.NetworkImageView;

import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.async.Volley;
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
}

@Override
public void update(AdapterPublisherEvent event) {
}
}
