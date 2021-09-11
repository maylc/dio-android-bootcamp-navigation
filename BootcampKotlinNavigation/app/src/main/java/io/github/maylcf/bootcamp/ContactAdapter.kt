package io.github.maylcf.bootcamp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val listener: ContactClickListener) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<Contact>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ContactAdapterViewHolder(itemView: View, var list: List<Contact>, var listener: ContactClickListener) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.contact_name)
        private val phone = itemView.findViewById<TextView>(R.id.contact_phone)
        private val image = itemView.findViewById<ImageView>(R.id.contact_photo)

        init {
            itemView.setOnClickListener {
                listener.onContactClick(list[adapterPosition])
            }
        }

        fun bind(contact: Contact) {
            name.text = contact.name
            phone.text = contact.phone
        }
    }
}