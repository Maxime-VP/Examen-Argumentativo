package com.example.kotlin.mydragonballapp.framework.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.mydragonballapp.R
import com.example.kotlin.mydragonballapp.databinding.ActivityMainBinding
import com.example.kotlin.mydragonballapp.framework.viewmodel.MainViewModel
import com.example.kotlin.mydragonballapp.framework.views.fragments.CharacterListFragment
import com.example.kotlin.mydragonballapp.framework.views.fragments.SearchFragment
import com.example.kotlin.mydragonballapp.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var currentFragment: Fragment? = null
    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeListeners()
        showInitialFragment()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun showInitialFragment() {
        // Inicializamos currentFragment con el fragmento de lista de personajes
        val characterListFragment = CharacterListFragment()
        currentFragment = characterListFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, characterListFragment)
            .commit()

        currentMenuOption = Constants.MENU_CHARACTER_LIST
    }

    private fun initializeListeners() {
        binding.appBarMain.llPersonajes.setOnClickListener {
            selectMenuOption(Constants.MENU_CHARACTER_LIST)
        }

        binding.appBarMain.llSearch.setOnClickListener {
            selectMenuOption(Constants.MENU_SEARCH)
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        // Validar si currentFragment es null para evitar crasheos
        if (currentFragment == null || newFragment::class != currentFragment!!::class) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, newFragment)
                .commit()

            currentFragment = newFragment
            currentMenuOption = newMenuOption
        }
    }

    private fun selectMenuOption(menuOption: String) {
        if (menuOption == currentMenuOption) {
            return
        }

        when (menuOption) {
            Constants.MENU_CHARACTER_LIST -> exchangeCurrentFragment(CharacterListFragment(), Constants.MENU_CHARACTER_LIST)
            Constants.MENU_SEARCH -> exchangeCurrentFragment(SearchFragment(), Constants.MENU_SEARCH)
        }
    }
}
