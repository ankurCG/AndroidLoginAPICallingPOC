package com.example.loginsignupdemo.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.loginsignupdemo.utils.DataBaseHelper
import com.example.loginsignupdemo.fragment.LoginFragment
import com.example.loginsignupdemo.R

class MainActivity : AppCompatActivity() {

    private lateinit var dataBaseHelper: DataBaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentReplace(LoginFragment())

       dataBaseHelper = DataBaseHelper(this)

    }
    private fun fragmentReplace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.signupFragmentContainer,fragment)
        fragmentTransaction.commit()

    }

}
