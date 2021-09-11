package io.github.maylcf.bootcamp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ContactClickListener {

    private val contactList: RecyclerView by lazy {
        findViewById(R.id.contact_list)
    }

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        bindView()
        fetchContactList()
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

    override fun onContactClick(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java)
        intent.putExtra(ContactDetailActivity.CONTACT_EXTRA, contact)
        startActivity(intent)
    }

    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun bindView() {
        contactList.adapter = adapter
        contactList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getContactList(): List<Contact> {
        val list = getSharedPreferencesInstance().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun updateList() {
        adapter.updateList(getContactList())
    }

    private fun getSharedPreferencesInstance(): SharedPreferences {
        return getSharedPreferences("io.github.maylc.bootcamp.preferences", Context.MODE_PRIVATE)
    }

    private fun fetchContactList() {
        val list = arrayListOf(
            Contact("Mary", "111-222-3333", ""),
            Contact("John", "123-456-7890", "")
        )

        getSharedPreferencesInstance().edit().putString("contacts", Gson().toJson(list)).apply()
    }
}