package io.github.maylcf.bootcamp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item_menu_2 -> {
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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