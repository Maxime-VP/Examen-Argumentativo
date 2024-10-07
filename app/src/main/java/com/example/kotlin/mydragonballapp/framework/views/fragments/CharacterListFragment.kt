package com.example.kotlin.mydragonballapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.R
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import com.example.kotlin.mydragonballapp.databinding.FragmentCharacterListBinding
import com.example.kotlin.mydragonballapp.framework.adapters.CharacterAdapter
import com.example.kotlin.mydragonballapp.framework.viewmodel.CharacterListViewModel


class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter: CharacterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]

        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeComponents(root)
        initializeObservers()
        viewModel.getCharacterList(1, 10) // Llamada inicial con página 1 y 10 personajes por página

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(
            R.id.RVCharacter)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { charactersObject: CharactersObject? ->
            if (charactersObject != null) {
                setUpRecyclerView(charactersObject.items)
            }
        }
    }

    private fun setUpRecyclerView(dataForList: ArrayList<CharacterBase>) {
        adapter.setData(dataForList, requireContext())
    }
}
