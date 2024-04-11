package com.group16.fitapp


import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


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