package pt.epcc.alunos.al220007.bloqs.ui.home

import org.json.JSONArray
import pt.epcc.alunos.al220007.bloqs.core.Iterator

class KeywordsIterator(array: JSONArray?) :
    Iterator<String, String, JSONArray>(array, KeywordsInstanciator()) {
    override fun size(array: JSONArray): Int {
        return array.length()
    }

    override fun createIterator(array: JSONArray?): Iterator<String, String, JSONArray> {
        return KeywordsIterator(array)
    }
}