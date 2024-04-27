package com.example.bmi.Activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.bmi.Fragments.HistoryFragment
import com.example.bmi.Fragments.HomeFragment
import com.example.bmi.R
import com.example.bmi.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityBaseBinding
    lateinit var toggle: ActionBarDrawerToggle

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
      navDrawer()
      replaceFrag(HomeFragment())

    }

    private fun navDrawer(){
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout, R.string.open, R.string.close)
        toggle.syncState()

        binding.menuIcon.setOnClickListener {
            binding.drawerLayout.open()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFrag(HomeFragment())
                R.id.about -> Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show()
                R.id.help -> Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show()
                R.id.feedback -> Toast.makeText(this, "Feedback Clicked", Toast.LENGTH_SHORT).show()
                R.id.history -> replaceFrag(HistoryFragment())
                R.id.share -> Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }

    private fun replaceFrag(fragment: Fragment){
        var manager = supportFragmentManager
        var transcation = manager.beginTransaction()

        transcation.replace(R.id.ReplaceableScreen,fragment).commit()
    }
}