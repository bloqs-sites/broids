package pt.epcc.alunos.al220007.bloqs.ui.home

import org.json.JSONArray
import org.json.JSONObject
import pt.epcc.alunos.al220007.bloqs.core.Iterator

class BloqsIterator(array: JSONArray?) :
    Iterator<Bloq, JSONObject, JSONArray>(array, BloqsInstanciator()) {
    override fun size(array: JSONArray): Int {
        return array.length()
    }

    override fun createIterator(array: JSONArray?): Iterator<Bloq, JSONObject, JSONArray> {
        return BloqsIterator(array)
    }
}