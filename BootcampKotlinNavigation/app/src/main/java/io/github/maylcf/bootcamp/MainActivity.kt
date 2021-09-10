package io.github.maylcf.bootcamp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val contactList: RecyclerView by lazy {
        findViewById(R.id.contact_list)
    }

    private val adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        updateList()
    }

    private fun bindView() {
        contactList.adapter = adapter
        contactList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList() {
        adapter.updateList(
                arrayListOf(
                        Contact("Mary", "111-222-3333", ""),
                        Contact("John", "123-456-7890", "")
                )
        )
    }
}