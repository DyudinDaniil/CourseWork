package com.daniildyudin.mentors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.daniildyudin.mentors.Models.User
import com.daniildyudin.mentors.databinding.ActivityEditBinding
import com.daniildyudin.mentors.databinding.ActivityProfileBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding
    var usersList = ArrayList<User>()
    lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, categories)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
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

    private fun changeUser(email: String, name: String, about: String, category: String, time: String) {
        for (index in 0 until usersList.size) {
            if (usersList[index].email == email) {
               usersList[index].name = name
                usersList[index].about = about
                usersList[index].category = category
                usersList[index].time = time
            }
        }
    }

    fun onClickSave(view: View) {
        val editName = binding.edName.text.toString()
        val editAbout = binding.edAbout.text.toString()
        val editTime = binding.edTime.text.toString()
        val editCategory = binding.autoCompleteTextView.text.toString()

        if (editName.isNotEmpty() && editAbout.isNotEmpty() && editTime.isNotEmpty() && editCategory.isNotEmpty()) {
            changeUser(userEmail, editName, editAbout, editCategory, editTime)
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
            intent.putExtra("user_email", userEmail)

            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, R.string.fields_mistake, Toast.LENGTH_SHORT).show()
        }
    }
}