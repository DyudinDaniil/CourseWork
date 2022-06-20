package com.daniildyudin.mentors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.databinding.ActivityRequestBinding

class RequestActivity : AppCompatActivity() {

    lateinit var binding: ActivityRequestBinding
    var usersList = ArrayList<User>()
    lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.feed -> {
                    intent = Intent(this, FeedActivity::class.java)

                    val emailList = ArrayList<String>()
                    val passwordList = ArrayList<String>()
                    val nameList = ArrayList<String>()
                    val aboutList = ArrayList<String>()
                    val categoryList = ArrayList<String>()
                    val timeList = ArrayList<String>()
                    for (item in usersList) {
                        emailList.add(item.email)
                        passwordList.add(item.password)
                        nameList.add(item.name)
                        aboutList.add(item.about)
                        categoryList.add(item.category)
                        timeList.add(item.time)
                    }
                    intent.putExtra("email_list", emailList)
                    intent.putExtra("password_list", passwordList)
                    intent.putExtra("name_list", nameList)
                    intent.putExtra("about_list", aboutList)
                    intent.putExtra("category_list", categoryList)
                    intent.putExtra("time_list", timeList)
                    intent.putExtra("user_email", userEmail)

                    startActivity(intent)
                    finish()
                }
                R.id.profile -> {
                    intent = Intent(this, ProfileActivity::class.java)

                    val emailList = ArrayList<String>()
                    val passwordList = ArrayList<String>()
                    val nameList = ArrayList<String>()
                    val aboutList = ArrayList<String>()
                    val categoryList = ArrayList<String>()
                    val timeList = ArrayList<String>()
                    for (item in usersList) {
                        emailList.add(item.email)
                        passwordList.add(item.password)
                        nameList.add(item.name)
                        aboutList.add(item.about)
                        categoryList.add(item.category)
                        timeList.add(item.time)
                    }
                    intent.putExtra("email_list", emailList)
                    intent.putExtra("password_list", passwordList)
                    intent.putExtra("name_list", nameList)
                    intent.putExtra("about_list", aboutList)
                    intent.putExtra("category_list", categoryList)
                    intent.putExtra("time_list", timeList)
                    intent.putExtra("user_email", userEmail)

                    startActivity(intent)
                    finish()
                }
            }
            true
        }

        init()
    }

    private fun init() {
        val intent = getIntent()
        var emailList = intent.getStringArrayListExtra("email_list")!!
        var passwordList = intent.getStringArrayListExtra("password_list")!!
        var nameList = intent.getStringArrayListExtra("name_list")!!
        var aboutList = intent.getStringArrayListExtra("about_list")!!
        var categoryList = intent.getStringArrayListExtra("category_list")!!
        var timeList = intent.getStringArrayListExtra("time_list")!!
        userEmail = intent.getStringExtra("user_email")!!
        for (index in 0 until emailList.size) {
            var indexUser = User()
            indexUser.email = emailList[index].toString()
            indexUser.password = passwordList[index].toString()
            indexUser.name = nameList[index].toString()
            indexUser.about = aboutList[index].toString()
            indexUser.category = categoryList[index].toString()
            indexUser.time = timeList[index].toString()
            usersList.add(indexUser)
        }
    }
}