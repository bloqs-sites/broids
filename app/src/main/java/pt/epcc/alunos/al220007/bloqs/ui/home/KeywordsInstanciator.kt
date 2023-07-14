package pt.epcc.alunos.al220007.bloqs.ui.home

import org.json.JSONArray
import pt.epcc.alunos.al220007.bloqs.core.Iterator

class KeywordsInstanciator : Iterator.Instanciator<String, String, JSONArray> {
    override fun init(): String {
        return ""
    }

    override fun next(array: JSONArray?, counter: Int): String? {
        return array?.getString(counter)
    }

    override fun fromJson(json: String?, o: String): String {
        return json.toString()
    }
}