package com.example.loginsignupdemo.View

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loginsignupdemo.fragment.ProductListFragment
import com.example.loginsignupdemo.R

class NextActivity:AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        fragmentReplace(ProductListFragment())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> {
            alertDialog()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun fragmentReplace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.productDetailsFragmentContainer,fragment)

        fragmentTransaction.commit()

    }

    private fun alertDialog(){
        val alert = AlertDialog.Builder(this@NextActivity)

        alert.setTitle("Log Out")
            .setMessage("Do you want to Log Out from the Application ?")
            .setIcon(R.drawable.img_logout)
            .setCancelable(true)
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val i = Intent(this@NextActivity, MainActivity::class.java)
                startActivity(i)
            })
        alert.create().show()
    }
}