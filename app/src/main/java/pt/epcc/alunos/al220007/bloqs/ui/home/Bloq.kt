package pt.epcc.alunos.al220007.bloqs.ui.home

import java.net.URL

class Bloq {
    var id: Int = -1
    lateinit var image: URL
    lateinit var name: String
    lateinit var category: String
    lateinit var description: String
    lateinit var tags: Array<String>
}