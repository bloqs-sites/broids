package pt.epcc.alunos.al220007.bloqs

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.multidex.MultiDex
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.epcc.alunos.al220007.bloqs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var actionBar: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onResume() {
        super.onResume()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_bloqs
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val instance: Spinner = binding.instance
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(applicationContext, R.layout.instance_item, items)
        instance.adapter = adapter
//        actionBar = binding.actionBar
//        actionBar.setContent {
//            Button(onClick = { }) {
//
//            }
//
//            TextField(
//                value = "https://bloqsenjin-ten.vercel.app/api/rest/bloq",
//                onValueChange = { v -> })
//
//            SearchBar(
//                query = "",
//                onQueryChange = { q -> },
//                onSearch = { q -> },
//                active = false,
//                onActiveChange = { active -> }) {
//
//            }
//        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }
}