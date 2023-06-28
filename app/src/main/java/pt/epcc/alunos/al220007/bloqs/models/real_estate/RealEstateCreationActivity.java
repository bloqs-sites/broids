package pt.epcc.alunos.al220007.bloqs.models.real_estate;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.NetworkImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jvmdbhelper.model.TableManager;
import pt.epcc.alunos.al220007.bloqs.R;
import pt.epcc.alunos.al220007.bloqs.core.async.Volley;
import pt.epcc.alunos.al220007.bloqs.list.CreationActivity;

public class RealEstateCreationActivity extends CreationActivity<RealEstate> {
private static final int DESCRIPTION_ID = R.id.description;
private static final int TYPOLOGY_ID = R.id.typology;
private static final int LOCATION_ID = R.id.location;
private static final int PHOTO_ID = R.id.photo;
private static final int PHOTO_IMAGE_ID = R.id.photo_image;

private TextView description, typology, location, photo;
private NetworkImageView photo_image;

@NonNull
@Override
protected TableManager<RealEstate> createManager() {
	return new RealEstateManager();
}

@Override
protected int formLayout() {
	return R.layout.activity_real_form;
}

@Nullable
@Override
protected Map<String, Object> createWhereClause(Intent intent) {
	if (intent == null) {
		return null;
	}

	long id = intent.getLongExtra("ID", Long.MAX_VALUE);
	if (id == Long.MAX_VALUE) {
		return null;
	}

	Map<String, Object> where = new HashMap<>();
	where.put(RealEstateManager.COL_ID, id);

	return where;
}

@Override
protected void findViews(@NonNull View view, @Nullable RealEstate o) {
	this.description = view.findViewById(DESCRIPTION_ID);
	this.typology = view.findViewById(TYPOLOGY_ID);
	this.location = view.findViewById(LOCATION_ID);
	this.photo = view.findViewById(PHOTO_ID);
	this.photo_image = view.findViewById(PHOTO_IMAGE_ID);

	if (o != null) {
		this.description.setText(o.getDescription());
		this.typology.setText(String.valueOf(o.getTypology()));
		this.location.setText(o.getLocation());
		this.photo.setText(o.getPhoto().toString());
		this.photo_image.setImageUrl(o.getPhoto().toString(), Volley.getInstance(() -> this).getImageLoader());
	}

	this.photo.setOnFocusChangeListener((v, hasFocus) -> {
		if (v == RealEstateCreationActivity.this.photo) {
			String url = RealEstateCreationActivity.this.photo.getText().toString();
			try {
				new URL(url);
			} catch (MalformedURLException e) {
				return;
			}

			RealEstateCreationActivity.this.photo_image.setImageUrl(
				url,
				Volley.getInstance(() -> RealEstateCreationActivity.this)
					.getImageLoader()
			);
		}
	});
}

@Override
protected void manageModel(@NonNull RealEstate o) {
	o.setDescription(String.valueOf(this.description.getText()));
	o.setTypology(Byte.parseByte(String.valueOf(this.typology.getText())));
	o.setLocation(String.valueOf(this.location.getText()));
	try {
		o.setPhoto(new URL(String.valueOf(this.photo.getText())));
	} catch (MalformedURLException e) {
		Toast.makeText(this, "Photo URL it's invalid", Toast.LENGTH_SHORT).show();
	}
}

@NonNull
@Override
protected Class<?> createRedirectClass() {
	return RealEstateActivity.class;
}
}