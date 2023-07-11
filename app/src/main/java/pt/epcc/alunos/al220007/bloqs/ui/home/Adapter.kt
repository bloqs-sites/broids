package pt.epcc.alunos.al220007.bloqs.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import pt.epcc.alunos.al220007.bloqs.R
import pt.epcc.alunos.al220007.bloqs.core.async.Volley

class Adapter(private val ctx: Context?, private val dataSet: List<Bloq>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(private val ctx: Context?, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val image: NetworkImageView
        private val name: TextView
        private val category: TextView
        private val description: TextView
        private val tags: ComposeView

        init {
            image = itemView.findViewById(R.id.image)
            name = itemView.findViewById(R.id.name)
            category = itemView.findViewById(R.id.category)
            description = itemView.findViewById(R.id.description)
            tags = itemView.findViewById(R.id.keywords)
        }

        @OptIn(ExperimentalFoundationApi::class)
        fun onBind(o: Bloq) {
            image.setImageUrl(o.image.toString(), Volley.getInstance { ctx }.imageLoader)
            name.text = o.name
            category.text = o.category
            description.text = o.description
            tags.setContent {
                Box {
                    val listState = rememberLazyListState()

                    LazyRow(
                        state = listState,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(
                            items = o.tags,
                            //key = { o.id },
                            contentType = { it }
                        ) {
                            Column(modifier = Modifier.animateItemPlacement()) {
                                Text(text = "#${it}")
                            }
                        }
                    }

//                    LaunchedEffect(listState) {
//                        snapshotFlow { listState.firstVisibleItemIndex }
//                            .map { index -> index > 0 }
//                            .distinctUntilChanged()
//                            .filter { it }
//                            .collect {
//                                MyAnalyticsService.sendScrolledPastFirstItemEvent()
//                            }

//                    val showButton by remember {
//                        derivedStateOf {
//                            listState.firstVisibleItemIndex > 0
//                        }
//                    }
//
//                    AnimatedVisibility(visible = showButton) {
//                        ScrollToTopButton()
//                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_home, parent, false)

        return ViewHolder(ctx, view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }
}