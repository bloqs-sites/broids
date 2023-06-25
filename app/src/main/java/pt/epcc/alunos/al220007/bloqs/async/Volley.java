package pt.epcc.alunos.al220007.bloqs.async;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONObject;

public class Volley {

public static final int LRU_CACHE_MAX_SIZE = 20;
private static Volley instance;
private static ContextSupplier ctx;
private final ImageLoader imageLoader;
private RequestQueue requestQueue;

private Volley(ContextSupplier ctx) {
	Volley.ctx = ctx;
	this.requestQueue = this.getRequestQueue();

	imageLoader = new ImageLoader(requestQueue,
		new ImageLoader.ImageCache() {
			private final LruCache<String, Bitmap>
				cache = new LruCache<>(LRU_CACHE_MAX_SIZE);

			@Override
			public Bitmap getBitmap(String url) {
				return cache.get(url);
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {
				cache.put(url, bitmap);
			}
		});
}

public static synchronized Volley getInstance(ContextSupplier ctx) {
	if (Volley.instance == null) {
		Volley.instance = new Volley(ctx);
	}

	return Volley.instance;
}

public void queue(Request<JSONObject> req) {
	getRequestQueue().add(req);
}

private RequestQueue getRequestQueue() {
	if (this.requestQueue == null) {
		this.requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(Volley.ctx
			.getContext()
			.getApplicationContext());
	}

	return this.requestQueue;
}

public ImageLoader getImageLoader() {
	return imageLoader;
}
}
