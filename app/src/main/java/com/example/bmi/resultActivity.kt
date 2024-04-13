package com.example.bmi

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmi.databinding.ActivityResultBinding

class resultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var age = intent.getStringExtra("age")
        var height = intent.getStringExtra("height")!!.toDouble() // Assuming height is already in meters
        var weight = intent.getStringExtra("weight")!!.toDouble()
        var click = intent.getStringExtra("click")

        binding.backBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


            // Proceed with BMI calculation using height and weight
            var bmiLevel = (weight / (height * height))

        if (click?.toInt()==0){

            binding.bmiValue.text = String.format("%.2f", bmiLevel)
            when (bmiLevel) {
                in Double.MIN_VALUE..18.5 ->
                    binding.bmiInLevel.text ="Underweight"

                in 18.5..24.9 -> binding.bmiInLevel.text ="Normal "
                in 25.0..29.9 -> binding.bmiInLevel.text ="Overweight"
                else -> binding.bmiInLevel.text ="Obesity"
            }
            if (bmiLevel<18.5){
                binding.bmiInLevel.setTextColor(Color.parseColor("#FFFF00"))
                binding.bmiValue.setTextColor(Color.parseColor("#FFFF00"))
            }
            else if (bmiLevel>=18.5 && bmiLevel<=24.9){
                binding.bmiInLevel.setTextColor(Color.parseColor("#00FF00"))
                binding.bmiValue.setTextColor(Color.parseColor("#00FF00"))

            }
            else if (bmiLevel>=25 && bmiLevel <=29.9){
                binding.bmiInLevel.setTextColor(Color.parseColor("#FFA500"))
                binding.bmiValue.setTextColor(Color.parseColor("#FFA500"))

            }
            else if(bmiLevel>=30.0){
                binding.bmiInLevel.setTextColor(Color.parseColor("#FF0000"))
                binding.bmiValue.setTextColor(Color.parseColor("#FF0000"))

            }

            binding.genderResult.text = "Gender           Male"
            binding.ageResult.text = "Age           $age"
            binding.heightResult.text = "Height           $height cm"
            binding.weightResult.text ="Weight           $weight kg"

        }
        else if(click?.toInt()==1){

            binding.bmiValue.text = String.format("%.2f", bmiLevel)
            when (bmiLevel) {
                in Double.MIN_VALUE..18.5 -> binding.bmiInLevel.text ="Underweight"
                in 18.5..24.9 -> binding.bmiInLevel.text ="Normal"
                in 25.0..29.9 -> binding.bmiInLevel.text ="Overweight"
                else -> binding.bmiInLevel.text ="Obesity"
            }

            binding.genderResult.text = "Gender           Female"
            binding.ageResult.text = "Age           $age"
            binding.heightResult.text = "Height           $height m"
            binding.weightResult.text ="Weight           $weight lbs"
        }

    }
}