package pt.epcc.alunos.al220007.bloqs.async;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONObject;

public class Volley {
private static Volley instance;
private static ContextSupplier ctx;
private RequestQueue requestQueue;

private Volley(ContextSupplier ctx) {
	Volley.ctx = ctx;
	this.requestQueue = this.getRequestQueue();
}

public static synchronized Volley getInstance(ContextSupplier ctx) {
	if (Volley.instance == null) {
		Volley.instance = new Volley(ctx);
	}

	return Volley.instance;
}

private RequestQueue getRequestQueue() {
	if (this.requestQueue == null) {
		this.requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(Volley.ctx
			.getContext()
			.getApplicationContext());
	}

	return this.requestQueue;
}

public void queue(Request<JSONObject> req) {
	getRequestQueue().add(req);
}
}
