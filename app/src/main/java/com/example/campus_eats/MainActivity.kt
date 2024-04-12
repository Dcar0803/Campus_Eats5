package com.example.campus_eats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var activeFragment: Fragment
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val flexBalanceFragment = FlexBalanceFragment()
        val reviewFragment = ReviewFragment()

        fragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, reviewFragment, "4").hide(reviewFragment)
            add(R.id.fragment_container, flexBalanceFragment, "3").hide(flexBalanceFragment)
            add(R.id.fragment_container, profileFragment, "2").hide(profileFragment)
            add(R.id.fragment_container, homeFragment, "1")
            activeFragment = homeFragment
        }.commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home_page -> {
                switchFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile_page -> {
                switchFragment(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.balance_page -> {
                switchFragment(3)
                return@OnNavigationItemSelectedListener true
            }
            R.id.review_page -> {
                switchFragment(4)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(fragmentNumber: Int) {
        val selectedFragment = fragmentManager.findFragmentByTag(fragmentNumber.toString()) ?: return

        fragmentManager.beginTransaction().hide(activeFragment).show(selectedFragment).commit()
        activeFragment = selectedFragment
    }
}
