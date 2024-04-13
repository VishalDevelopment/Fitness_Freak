package com.example.bmi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
     var click = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LogoShow()

        inputData()


    }

    private fun inputData() {

        val age = binding.ageInput.text
        val height = binding.heightInput.text
        val weight = binding.weightInput.text

        binding.calculate.setOnClickListener {
            if (age.isNotBlank()&& height.isNotBlank() && weight.isNotBlank()) {
                Toast.makeText(this, "$age $height $weight", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,resultActivity::class.java)
                intent.putExtra("click",click.toString())
                intent.putExtra("age", age.toString())
                intent.putExtra("height", height.toString())
                intent.putExtra("weight", weight.toString())
                startActivity(intent)


            } else {
                Toast.makeText(this, "Empty field not allowed", Toast.LENGTH_SHORT).show()
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


