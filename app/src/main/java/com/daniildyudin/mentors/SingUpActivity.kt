package com.daniildyudin.mentors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.databinding.ActivitySingUpBinding

class SingUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySingUpBinding
    var usersList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun onClickMake(view: View) {
        val email = binding.edSignUpEmail.text.toString()
        val password = binding.edSignUpPassword.text.toString()
        val name = binding.edSignUpName.text.toString()
        val about = ""
        val category = ""
        val time = ""
        val newUser = User()
        newUser.email = email
        newUser.password = password
        newUser.name = name
        newUser.about = about
        newUser.category = category
        newUser.time = time
        if (newUser.email.isNotEmpty() && newUser.password.isNotEmpty() && newUser.name.isNotEmpty() && uniqueUser(newUser)) {
            usersList.add(newUser)
            val intent = Intent(this, MainActivity::class.java)

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
            finish()
        } else if (!uniqueUser(newUser)) {
            Toast.makeText(this, R.string.email_mistake, Toast.LENGTH_SHORT).show()
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

    private fun uniqueUser(user: User): Boolean {
        for (item in usersList) {
            if (item.email == user.email) {
                return false
            }
        }
        return true
    }
}