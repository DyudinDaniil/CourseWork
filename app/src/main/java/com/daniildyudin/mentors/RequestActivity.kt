package com.daniildyudin.mentors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniildyudin.mentors.databinding.ActivityRequestBinding

class RequestActivity : AppCompatActivity() {

    lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.feed -> {
                    intent = Intent(this, FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.profile -> {
                    intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }
    }
}