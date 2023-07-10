package com.example.loginsignupdemo.fragment

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loginsignupdemo.utils.DataBaseHelper
import com.example.loginsignupdemo.R


class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var db: SQLiteDatabase//
    lateinit var databaseHelper : DataBaseHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DataBaseHelper(requireContext())

        val edtFirstName: EditText = view.findViewById(R.id.edtFirstName)
        val edtLastName: EditText = view.findViewById(R.id.edtLastName)
        val edtPass: EditText = view.findViewById(R.id.edtPass)
        val edtCnfPass: EditText = view.findViewById(R.id.edtCnfPass)
        val edtEmail: EditText = view.findViewById(R.id.edtMail)
        val btnSignUp: Button = view.findViewById(R.id.btnSignup)
        val btnBack : Button = view.findViewById(R.id.btnBack)


        btnSignUp.setOnClickListener {

            val strFirstName: String = edtFirstName.text.toString()
            val strLastName: String = edtLastName.text.toString()
            val strMail: String = edtEmail.text.toString()
            val strPass: String = edtPass.text.toString()
            val strCnfPass: String = edtCnfPass.text.toString()


            if (strFirstName.isEmpty() || strPass.isEmpty() || strMail.isEmpty() || strLastName.isEmpty() || strCnfPass.isEmpty()) {
                Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                //return
            } else if (strPass != strCnfPass) {
                Toast.makeText(activity, "Password must be same", Toast.LENGTH_SHORT).show()

            }else if (!Patterns.EMAIL_ADDRESS.matcher(strMail).matches()){
                Toast.makeText(activity, "Invalid Email", Toast.LENGTH_SHORT).show()
            }
            else {
                val rowId = databaseHelper.addUser(strFirstName,strLastName,strMail,strPass)
                Log.i("info","rowID $rowId")
                Log.i("First name", strFirstName)


                if (rowId > -1) {
                    Toast.makeText(activity, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                   // Log.i("IF LOOP"," in IF LOOP")
                }

                edtFirstName.setText("")
                edtLastName.setText("")
                edtEmail.setText("")
                edtPass.setText("")
                edtCnfPass.setText("")
                fragmentReplace(LoginFragment())

            }
        }
        btnBack.setOnClickListener {
            fragmentReplace(LoginFragment())
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
        fun fragmentReplace(fragment: Fragment){
            val fragmentManager = activity?.supportFragmentManager
            val f1 : FrameLayout? = view?.findViewById(R.id.signupFragmentContainer)
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.signupFragmentContainer,fragment)
            fragmentTransaction?.commit()
        }
}



