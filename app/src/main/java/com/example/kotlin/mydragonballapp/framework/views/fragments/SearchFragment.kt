package com.example.kotlin.mydragonballapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin.mydragonballapp.databinding.FragmentSearchBinding
import com.example.kotlin.mydragonballapp.framework.adapters.CharacterAdapter
import com.example.kotlin.mydragonballapp.framework.viewmodel.CharacterListViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = CharacterAdapter(requireContext())
        binding.rvSearchResults.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvSearchResults.adapter = adapter

        initializeObservers()
        initializeSearchListener()

        return binding.root
    }

    private fun initializeObservers() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { charactersList ->
            charactersList?.let {
                adapter.setData(it)
            }
        }
    }

    private fun initializeSearchListener() {
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchCharactersByName(query)
            } else {
                Toast.makeText(requireContext(), "Ingrese un nombre para buscar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
