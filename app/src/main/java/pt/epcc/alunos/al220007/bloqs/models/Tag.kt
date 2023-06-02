package pt.epcc.alunos.al220007.bloqs.models

import jvmdbhelper.db_defenitions.Column
import jvmdbhelper.db_defenitions.Table
import jvmdbhelper.db_defenitions.Type
import jvmdbhelper.model.TableManager
import jvmdbhelper.model.Values

class Tag : Model {
    var name: String? = null
    private var id: Long = 0

    override fun fromMap(values: Values) {
        val id = values[COL_ID]
        if (id is Long) {
            this.id = id
        }
        this.name = values[COL_NAME] as String?
    }

    override fun getImmutable(): Values = mapOf(
        COL_ID to this.id
    )

    override fun getMutable(): Values = mapOf(
        COL_NAME to this.name as Any
    )

    override fun getId(): Long = this.id

    companion object : TableManager<Tag>() {
        private const val NAME = "tags"
        const val COL_ID = "_id"
        const val COL_NAME = "name"
        override val name: String
            get() = NAME

        override fun init(): Tag = Tag()

        override fun manageTable(table: Table) {
            table.addPK(COL_ID).addColumn(
                Column(COL_NAME, Type.TXT).apply {
                    unique = true
                    nullable = false
                }
            )
        }
    }
}