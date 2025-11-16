package com.bodega.delivery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bodega.delivery.R
import com.bodega.delivery.databinding.FragmentClientesBinding
import com.bodega.delivery.ui.adapters.ClienteAdapter
import com.bodega.delivery.viewmodel.ClienteViewModel

class ClientesFragment : Fragment() {

    private var _binding: FragmentClientesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClienteViewModel by viewModels()
    private lateinit var clienteAdapter: ClienteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        setupFab()

        viewModel.clientes.observe(viewLifecycleOwner) {
            clienteAdapter.submitList(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.setSearchQuery("") // Clear search on refresh
        }
    }

    private fun setupRecyclerView() {
        clienteAdapter = ClienteAdapter(
            onEditClick = { cliente ->
                // Lógica para editar cliente (abrir diálogo)
            },
            onDeleteClick = { cliente ->
                // Lógica para eliminar cliente (mostrar confirmación)
            }
        )
        binding.recyclerView.apply {
            adapter = clienteAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText.orEmpty())
                return true
            }
        })
    }

    private fun setupFab() {
        binding.fabAddCliente.setOnClickListener {
            // Lógica para agregar un nuevo cliente (abrir diálogo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}