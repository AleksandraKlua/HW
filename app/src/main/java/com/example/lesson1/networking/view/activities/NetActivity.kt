package com.example.lesson1.networking.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lesson1.R
import com.example.lesson1.networking.view.fragments.MenuFragment

class NetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)
        if (savedInstanceState == null) {
            val menuFragment: Fragment =
                MenuFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.container, menuFragment).commit()
        }
    }
}