package io.github.maylcf.bootcamp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ContactDetailActivity : AppCompatActivity() {

    private var contact: Contact? = null

    companion object {
        const val CONTACT_EXTRA = "CONTACT_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        initToolbar()
        getExtra()
        bindViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtra() {
        contact = intent.getParcelableExtra(CONTACT_EXTRA)
    }

    private fun bindViews() {
        findViewById<TextView>(R.id.contact_details_name).text = contact?.name
        findViewById<TextView>(R.id.contact_details_phone).text = contact?.phone
    }
}