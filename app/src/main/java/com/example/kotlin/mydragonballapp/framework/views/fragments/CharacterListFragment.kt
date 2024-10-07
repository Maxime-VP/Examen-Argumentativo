package com.example.kotlin.mydragonballapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin.mydragonballapp.databinding.FragmentCharacterListBinding
import com.example.kotlin.mydragonballapp.framework.adapters.CharacterAdapter
import com.example.kotlin.mydragonballapp.framework.viewmodel.CharacterListViewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        adapter = CharacterAdapter()
        binding.RVCharacter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.RVCharacter.adapter = adapter

        initializeObservers()
        viewModel.getCharacterList(1, 10)

        return binding.root
    }

    private fun initializeObservers() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { charactersObject ->
            charactersObject?.let {
                adapter.setData(it.items, requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
