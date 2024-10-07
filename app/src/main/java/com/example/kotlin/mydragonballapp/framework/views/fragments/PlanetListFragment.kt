package com.example.kotlin.mydragonballapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.mydragonballapp.databinding.FragmentPlanetListBinding
import com.example.kotlin.mydragonballapp.framework.adapters.PlanetAdapter
import com.example.kotlin.mydragonballapp.framework.viewmodel.PlanetListViewModel

class PlanetListFragment : Fragment() {

    private var _binding: FragmentPlanetListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PlanetListViewModel
    private lateinit var adapter: PlanetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[PlanetListViewModel::class.java]
        adapter = PlanetAdapter()

        binding.RVPlanet.layoutManager = LinearLayoutManager(requireContext())
        binding.RVPlanet.adapter = adapter

        viewModel.planetLiveData.observe(viewLifecycleOwner) { planets ->
            planets?.let {
                adapter.setData(it, requireContext()) // Pasar el contexto al adapter
            }
        }

        viewModel.getPlanets()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
