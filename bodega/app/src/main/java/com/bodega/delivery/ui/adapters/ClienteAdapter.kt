package com.bodega.delivery.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bodega.delivery.data.entities.Cliente
import com.bodega.delivery.databinding.ItemClienteBinding

class ClienteAdapter(
    private val onEditClick: (Cliente) -> Unit,
    private val onDeleteClick: (Cliente) -> Unit
) : ListAdapter<Cliente, ClienteAdapter.ClienteViewHolder>(ClienteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val binding = ItemClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = getItem(position)
        holder.bind(cliente, onEditClick, onDeleteClick)
    }

    class ClienteViewHolder(private val binding: ItemClienteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cliente: Cliente, onEditClick: (Cliente) -> Unit, onDeleteClick: (Cliente) -> Unit) {
            binding.textViewNombreCliente.text = "${cliente.nombre} ${cliente.apellido}"
            binding.textViewEmailCliente.text = cliente.email
            binding.textViewTelefonoCliente.text = cliente.telefono

            binding.buttonEdit.setOnClickListener { onEditClick(cliente) }
            binding.buttonDelete.setOnClickListener { onDeleteClick(cliente) }
        }
    }

    private class ClienteDiffCallback : DiffUtil.ItemCallback<Cliente>() {
        override fun areItemsTheSame(oldItem: Cliente, newItem: Cliente):
        Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cliente, newItem: Cliente):
        Boolean {
            return oldItem == newItem
        }
    }
}