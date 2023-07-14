package pt.epcc.alunos.al220007.bloqs.ui.home

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray

class Request(
    url: String?,
    listener: Response.Listener<JSONArray>?,
    errorListener: Response.ErrorListener?
) : JsonArrayRequest(url, listener, errorListener) {
}