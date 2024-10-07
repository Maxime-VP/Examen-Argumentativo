package com.example.kotlin.mydragonballapp.framework.views.activities

import android.os.Bundle
import com.example.kotlin.mydragonballapp.framework.viewmodel.MainViewModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.mydragonballapp.R
import com.example.kotlin.mydragonballapp.databinding.ActivityMainBinding
import com.example.kotlin.mydragonballapp.framework.views.fragments.CharacterListFragment
import com.example.kotlin.mydragonballapp.framework.views.fragments.SearchFragment
import com.example.kotlin.mydragonballapp.utils.Constants



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var currentFragment: Fragment

    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        initializeListeners()

        // Mostrar el fragmento de lista de personajes al iniciar
        exchangeCurrentFragment(CharacterListFragment(), Constants.MENU_CHARACTER_LIST)
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        // Aquí se pueden añadir observadores si hay algún LiveData en el ViewModel que queremos monitorear.
    }

    private fun initializeListeners() {
        // Listener para la opción de la lista de personajes
        binding.appBarMain.llPokedex.setOnClickListener {
            selectMenuOption(Constants.MENU_CHARACTER_LIST)
        }

        // Listener para la opción de búsqueda
        binding.appBarMain.llSearch.setOnClickListener {
            selectMenuOption(Constants.MENU_SEARCH)
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()

        currentMenuOption = newMenuOption
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
