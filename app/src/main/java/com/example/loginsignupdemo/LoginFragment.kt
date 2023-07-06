package com.example.loginsignupdemo

import android.annotation.SuppressLint
//import android.arch.lifecycle.ViewModelProviders
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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.loginsignupdemo.databinding.FragmentLoginBinding
import com.example.loginsignupdemo.ui.auth.AuthListner
import com.example.loginsignupdemo.ui.auth.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory

class LoginFragment :Fragment(R.layout.fragment_login),AuthListner{

    private lateinit var binding:FragmentLoginBinding
    //val viewModel = AuthViewModel
    private lateinit var viewModel: AuthViewModel
    private lateinit var dataBaseHelper : DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedIntanceState: Bundle?
    ): View? {
        val binding:FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_login)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

       //val viewModel = ViewModelProviders.of(this)[AuthViewModel::class.java]
       binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
/*

        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                // Login successful
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                // findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                // Show login error message
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
            }
        })

 */


        return binding!!.root
    }


    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //viewModel.authListner = this


        dataBaseHelper = DataBaseHelper(requireContext())
/*


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
                    }else{
                        Toast.makeText(activity, "Invalid Credentials !", Toast.LENGTH_SHORT)
                            .show()
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

 */



    }






    private fun fragmentReplaceExtra(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val f1 : FrameLayout? = view?.findViewById(R.id.signupFragmentContainer)
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.signupFragmentContainer,fragment)
        fragmentTransaction?.commit()
    }

     override fun onStarted() {
        Toast.makeText(activity,"Login Started",Toast.LENGTH_SHORT).show()
    }

     override fun onSuccess() {
        Toast.makeText(activity,"Login Success",Toast.LENGTH_SHORT).show()
    }

     override fun onFailure() {
        Toast.makeText(activity,"Login Failure",Toast.LENGTH_SHORT).show()
    }

     override fun fragmentReplace(fragment:Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        //val f1 : FrameLayout? = view?.findViewById(R.id.signupFragmentContainer)
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.loginFragmentContainer,fragment)
        fragmentTransaction?.commit()
    }


}
