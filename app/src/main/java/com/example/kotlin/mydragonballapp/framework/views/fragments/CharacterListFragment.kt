package com.example.kotlin.mydragonballapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.databinding.FragmentCharacterListBinding
import com.example.kotlin.mydragonballapp.framework.adapters.CharacterAdapter
import com.example.kotlin.mydragonballapp.framework.viewmodel.CharacterListViewModel
import com.example.kotlin.mydragonballapp.utils.Constants

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

        adapter = CharacterAdapter(requireContext())
        binding.RVCharacter.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.RVCharacter.adapter = adapter

        initializeObservers()
        setupPagination()
        viewModel.getCharacterList(Constants.MAX_CHARACTERS_PER_PAGE)

        return binding.root
    }

    private fun initializeObservers() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { charactersList ->
            charactersList?.let {
                adapter.setData(it) // Aquí no debería haber problema si coincidimos los tipos
            }
        }
    }

    private fun setupPagination() {
        binding.RVCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition + 2 >= totalItemCount) {
                    viewModel.getCharacterList(Constants.MAX_CHARACTERS_PER_PAGE)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
