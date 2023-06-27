package com.example.loginsignupdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity

class LoginFragment :Fragment(R.layout.fragment_login) {

    private lateinit var dataBaseHelper : DataBaseHelper

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBaseHelper = DataBaseHelper(requireContext())


        val mailid: EditText? = view.findViewById(R.id.editMailId)
        val password: EditText? = view.findViewById(R.id.editPassword)
        val checkBox: CheckBox? = view.findViewById(R.id.checkBox)
        val btnLogin: Button? = view.findViewById(R.id.btnLogin)
        val createAccount: TextView? = view.findViewById(R.id.createAccount)


        btnLogin?.setOnClickListener {
            val strEmail:String = mailid?.text.toString().trim()
            val strPassword:String = password?.text.toString().trim()

            if (checkBox!!.isChecked) {
                //Log.i("in box",strEmail)
                //Log.i("in box",strPassword)

                if (strEmail.isEmpty() ||strPassword.isEmpty()){
                    Toast.makeText(activity,"Please enter Email and Password",Toast.LENGTH_SHORT).show()
                }

                if (strEmail.isNotEmpty() && strPassword.isNotEmpty()) {
                    val mailCursor: Cursor? = dataBaseHelper.getCursor(strEmail)

                    Log.i("STR mail",strEmail)

                    if (mailCursor != null && mailCursor.count > 0) {
                        mailCursor.moveToFirst()
                        val savedPassword = mailCursor.getString(mailCursor.getColumnIndex(DataBaseHelper.COLUMN_PASSWORD))
                        if (strPassword == savedPassword) {
                            Log.i("START",strPassword)
                            Toast.makeText(activity,"Login Successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(activity, NextActivity::class.java))
                        } else {
                            Toast.makeText(activity, "Invalid Credentials !", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                }
            }else {
                Toast.makeText(activity, "Please Agree to terms and conditions", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        createAccount?.setOnClickListener {
            fragmentReplace(SignUpFragment())

        }

    }


    private fun fragmentReplace(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val f1 : FrameLayout? = view?.findViewById(R.id.signupFragmentContainer)
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.signupFragmentContainer,fragment)
        fragmentTransaction?.commit()
    }


}
