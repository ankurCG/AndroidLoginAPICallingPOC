package com.example.loginsignupdemo.ui.auth

import android.app.Application
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.loginsignupdemo.SignUpFragment
import kotlinx.coroutines.launch


class AuthViewModel:ViewModel() {
    var email: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var cnfPassword: String? = null

    var authListner: AuthListner? = null


    fun onCheckboxClicked(view: View): Boolean {
        return true
    }

    fun onButtonClick(view: View) {
        authListner?.onStarted()
        if (onCheckboxClicked(view)) {
            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                //failure
                authListner?.onFailure()

            }
            //success
            authListner?.onSuccess()
        } else {
            authListner?.onFailure()
        }
    }

    fun onCreateAccountClick(view: View) {
        // authListner?.onSuccess()
        authListner?.fragmentReplace(SignUpFragment())

    }

    fun onSignUpButtonClicked(): Boolean {

        if (firstName?.isEmpty() == true || password?.isEmpty() == true || email?.isEmpty() == true || lastName?.isEmpty() == true || cnfPassword?.isEmpty() == true) {
            //Toast.makeText(, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        } else return password == cnfPassword

    }


}
/*

//class AuthViewModel(private val userDao: UserDao) : ViewModel()
class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var email: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var cnfPassword: String? = null
    private val userDao: UserDao = AppDatabase.getInstance(application).userDao()
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmailAndPassword(email, password)
            _loginSuccess.postValue(user != null)
        }
    }
    fun onCheckboxClicked(view: View): Boolean {
        return true
    }

    fun onButtonClick(view: View) {
        //authListner?.onStarted()
        if (onCheckboxClicked(view)) {
            if (email?.isNotEmpty() == true || password?.isNotEmpty() == true) {
                //failure
                //authListner?.onFailure()
                login(email!!,password!!)

            }
            //success
            //authListner?.onSuccess()
        } else {
           // authListner?.onFailure()
        }
    }
}

 */

