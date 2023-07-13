package pt.epcc.alunos.al220007.bloqs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.json.JSONArray
import org.json.JSONObject
import pt.epcc.alunos.al220007.bloqs.core.JSONArrayIterator
import pt.epcc.alunos.al220007.bloqs.core.async.Volley
import pt.epcc.alunos.al220007.bloqs.databinding.FragmentHomeBinding
import java.net.URL

class HomeFragment : Fragment(), Response.Listener<JSONArray>, Response.ErrorListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: CircularProgressIndicator

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        recyclerView = binding.bloqs
        recyclerView.layoutManager = LinearLayoutManager(context)
        progress = binding.progress

        val url = URL("https://bloqsenjin-ten.vercel.app/api/rest/bloq")

        val req = Request(url.toString(), this, this)

        Volley.getInstance { context }.queue(req)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResponse(response: JSONArray?) {
        recyclerView.adapter = Adapter(
            context,
            JSONArrayIterator(response, object : JSONArrayIterator.Instanciator<Bloq> {
                override fun init(): Bloq = Bloq()

                override fun fromJson(json: JSONObject?, o: Bloq): Bloq {
                    return o
                }
            }).toList()
        )
        progress.visibility = View.GONE
    }

    override fun onErrorResponse(error: VolleyError?) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
        progress.visibility = View.GONE
    }
}