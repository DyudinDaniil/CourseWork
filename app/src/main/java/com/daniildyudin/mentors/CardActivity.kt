package com.daniildyudin.mentors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.databinding.ActivityCardBinding

class CardActivity : AppCompatActivity() {

    lateinit var binding: ActivityCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("item_name")
        val about = intent.getStringExtra("item_about")
        val category = intent.getStringExtra("item_category")
        val time = intent.getStringExtra("item_time")
        binding.apply {
            tvName.text = name
            tvAbout.text = about
            tvCategory.text = category
            tvTime.text = time
        }
    }

    fun onClickRequest(view: View) {
        Toast.makeText(this, "Заявка отправленна", Toast.LENGTH_SHORT).show()
        finish()
    }
}