package pt.epcc.alunos.al220007.bloqs.ui.home

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import pt.epcc.alunos.al220007.bloqs.core.Iterator
import java.net.URL
import kotlin.random.Random

class BloqsInstanciator : Iterator.Instanciator<Bloq, JSONObject, JSONArray> {
    override fun init(): Bloq = Bloq()

    override fun next(array: JSONArray?, counter: Int): JSONObject? {
        return array?.getJSONObject(counter)
    }

    override fun fromJson(json: JSONObject?, o: Bloq): Bloq? {
        if (json == null) {
            return null
        }
        try {
            o.id = json.getInt("id")
        } catch (_: JSONException) {
            return null
        }
        o.name = json.getString("name") ?: ""
        o.description = json.getString("description") ?: ""
        o.category = ""
        o.tags = KeywordsIterator(json.getJSONArray("keywords")).toList().toTypedArray()
        try {
            Log.d("JSON", json.getString("image"))
            o.image =
                URL("https://bloqsenjin-ten.vercel.app/api/image/${json.getString("image")}")
        } catch (_: JSONException) {
            o.image = URL("https://picsum.photos/200?random=${Random.nextInt()}")
        }
        return o
    }
}