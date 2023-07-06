package com.example.loginsignupdemo

import android.R.attr.password
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.loginsignupdemo.databinding.FragmentSignUpBinding
import com.example.loginsignupdemo.ui.auth.AuthListner
import com.example.loginsignupdemo.ui.auth.AuthViewModel
import com.example.loginsignupdemo.ui.auth.signUpViewModel


class SignUpFragment : Fragment(R.layout.fragment_sign_up),AuthListner{

    private lateinit var db: SQLiteDatabase//
    lateinit var databaseHelper : DataBaseHelper
    //val viewModel : signUpViewModel by viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

/*
        //viewModel.authListner = this
        binding.btnSignup.setOnClickListener {
            val fname = binding.edtFirstName.text.toString()
            val lname = binding.edtLastName.text.toString()
            val email = binding.edtMail.text.toString()
            val password = binding.edtPass.text.toString()
            viewModel.signup(email,password,fname,lname)
        }


        if( viewModel.onSignUpButtonClicked()){
           Toast.makeText(activity,"Toast testing success",Toast.LENGTH_SHORT).show()
       }else{
           Toast.makeText(activity,"Toast testing failure",Toast.LENGTH_SHORT).show()

       }

         */

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val binding:FragmentSignUpBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_sign_up)
        //val viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(signUpViewModel::class.java)
        //val viewModel : signUpViewModel by viewModel()
        //binding.viewModelSignup = viewModel



/*
        val binding: FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_sign_up)
        val viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListner = this

 */

        databaseHelper = DataBaseHelper(requireContext())

        /*

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

         */



    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }

     override fun onStarted() {
        TODO("Not yet implemented")
    }

     override fun onSuccess() {
        TODO("Not yet implemented")
    }

     override fun onFailure() {
        TODO("Not yet implemented")
    }

     override fun fragmentReplace(fragment: Fragment){
            val fragmentManager = activity?.supportFragmentManager
            //val f1 : FrameLayout? = view?.findViewById(R.id.loginFragmentContainer)
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.loginFragmentContainer,fragment)
            fragmentTransaction?.commit()
        }
}



