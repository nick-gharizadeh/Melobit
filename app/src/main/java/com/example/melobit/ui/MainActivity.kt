package com.example.melobit.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.melobit.R
import com.example.melobit.databinding.ActivityMainBinding
import com.example.melobit.ui.homefragment.HomeFragment
import com.example.melobit.ui.searchfragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.groupSplashScreen.alpha = 0f
        binding.groupSplashScreen.animate().setDuration(3000).alpha(1f).withEndAction {
            binding.groupSplashScreen.visibility = View.GONE
            binding.groupFragments.visibility = View.VISIBLE
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()


        setCurrentFragment(homeFragment)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.search -> setCurrentFragment(searchFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

}
