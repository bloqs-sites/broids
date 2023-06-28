package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import pt.epcc.alunos.al220007.bloqs.R;

public class RealEstateCreationViewHolder extends RealEstateViewHolder {
public RealEstateCreationViewHolder(Context ctx, @NonNull View itemView) {
	super(ctx, itemView);
}

@Override
protected void manageItemView() {
	super.manageItemView();

	Button edit = this.itemView.findViewById(R.id.edit);
	edit.setOnClickListener(this::edit);

	Button delete = this.itemView.findViewById(R.id.delete);
	delete.setOnClickListener(v -> this.getPublisherProxy().delete(this, new RealEstateManager()));
}

private void edit(View v) {
	Intent i = new Intent(this.getContext(), RealEstateCreationActivity.class);
	i.putExtra("ID", this.getModel().getId());
	this.getContext().startActivity(i);
}
}
