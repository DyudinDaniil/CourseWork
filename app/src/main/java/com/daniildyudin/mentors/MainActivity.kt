package com.daniildyudin.mentors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var usersList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var testUser = User()
        testUser.email = "test"
        testUser.password = "test"
        testUser.name = "test"
        usersList.add(testUser)

        intent = getIntent()
        var emailList = intent.getStringArrayListExtra("email_list")
        if (emailList != null) {
            init()
        }
    }

    fun onClickRegister(view: View) {
        val intent = Intent(this, SingUpActivity::class.java)

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

        startActivity(intent)
    }

    fun onClickDone(view: View) {
        val email = binding.edSignInEmail.text.toString()
        val password = binding.edSignInPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty() && successfulLogin(email, password)) {
            val intent = Intent(this, ProfileActivity::class.java)

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
            intent.putExtra("user_email", binding.edSignInEmail.text.toString())

            startActivity(intent)
            finish()
        } else if (email.isNotEmpty() && password.isNotEmpty() && !successfulLogin(email, password)) {
            Toast.makeText(this, R.string.user_mistake, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.fields_mistake, Toast.LENGTH_SHORT).show()
        }
    }

    private fun init() {
        val intent = getIntent()

        var emailList = intent.getStringArrayListExtra("email_list")!!
        var passwordList = intent.getStringArrayListExtra("password_list")!!
        var nameList = intent.getStringArrayListExtra("name_list")!!
        var aboutList = intent.getStringArrayListExtra("about_list")!!
        var categoryList = intent.getStringArrayListExtra("category_list")!!
        var timeList = intent.getStringArrayListExtra("time_list")!!

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

    private fun successfulLogin(email: String, password: String): Boolean {
        for (item in usersList) {
            if (item.email == email && item.password == password) {
                return true
            }
        }
        return false
    }

//    private fun findIndex(email: String, password: String): Int? {
//        for (index in 0 until usersList.size) {
//            if (usersList[index].email == email && usersList[index].password == password) {
//                return index
//            }
//        }
//        return 0
//    }
}