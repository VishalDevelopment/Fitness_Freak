package com.example.bmi

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var dialog:Dialog
     var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navDrawer()
        LogoShow()
        inputData()


    }
    private fun navDrawer(){
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        toggle.syncState()

        binding.menuIcon.setOnClickListener {
            binding.drawerLayout.open()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(this, "About Clicked", Toast.LENGTH_SHORT).show()
                R.id.help -> Toast.makeText(this, "Help Clicked", Toast.LENGTH_SHORT).show()
                R.id.feedback -> Toast.makeText(this, "Feedback Clicked", Toast.LENGTH_SHORT).show()
                R.id.history -> Toast.makeText(this, "History Clicked", Toast.LENGTH_SHORT).show()
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
    private fun inputData() {

        val age = binding.ageInput.text
        val height = binding.heightInput.text
        val weight = binding.weightInput.text

        binding.calculate.setOnClickListener {
            if (age.isNotBlank()&& height.isNotBlank() && weight.isNotBlank()) {
                val intent = Intent(this,resultActivity::class.java)
                intent.putExtra("click",click.toString())
                intent.putExtra("age", age.toString())
                intent.putExtra("height", height.toString())
                intent.putExtra("weight", weight.toString())
                startActivity(intent)


            } else {

               dialog = Dialog(this)
                dialog.setContentView(R.layout.custom_dialogue)
                dialog.show()
                var okayBtn = dialog.findViewById<Button>(R.id.okayBtn)

                okayBtn.setOnClickListener {
                    dialog.dismiss()
                }

            }
        }

    }
    private fun LogoShow() {


            binding.femaleBtn.setOnClickListener {
                binding.womanLogo.visibility = View.VISIBLE
                binding.manLogo.visibility = View.INVISIBLE
                click = 1
            }


                binding.maleBtn.setOnClickListener {
                    binding.womanLogo.visibility = View.INVISIBLE
                    binding.manLogo.visibility = View.VISIBLE
                    click = 0

                }
        }
    }


