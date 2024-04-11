package com.group16.fitapp


import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.group16.fitapp.ui.theme.FitAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment= ExerciseFragment()
        val secondFragment= HomeFragment()
        val thirdFragment= UserFragment()

        setCurrentFragment(secondFragment)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)


        bottomNavigationView.setOnItemSelectedListener {item ->
            lateinit var fragment: Fragment
            when(item.itemId){
                R.id.navigation_exercises->setCurrentFragment(firstFragment)
                R.id.navigation_home->setCurrentFragment(secondFragment)
                R.id.navigation_user->setCurrentFragment(thirdFragment)

            }

            true
        }




    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.article_frame_layout,fragment)
            commit()
        }

}