package com.example.reusabelpostfeed

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reusabelpostfeed.databinding.ActivityMainBinding
import com.example.reusabelpostfeed.ui.theme.ReusabelPostFeedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnListFeed.setOnClickListener {
            openFeedActivity(getString(R.string.listfeed))
        }

        binding.btnGridFeed.setOnClickListener {
            openFeedActivity(getString(R.string.gridfeed))
        }

        binding.btnCompactFeed.setOnClickListener {
            openFeedActivity(getString(R.string.compactlistfeed))
        }
    }

    private fun openFeedActivity(configType: String) {
        val intent = Intent(this, FeedActivity::class.java)
        intent.putExtra(getString(R.string.config_type),configType)
        startActivity(intent)
    }
}

