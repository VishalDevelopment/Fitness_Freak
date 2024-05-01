package com.example.bmi.Activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.bmi.RoomDb.BmiDatabase
import com.example.bmi.RoomDb.BmiTable
import com.example.bmi.databinding.ActivityResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

class resultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    lateinit var db: BmiDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BmiDatabase.getDatabase(this)

        var age = intent.getStringExtra("age")
        var height =
            intent.getStringExtra("height")!!.toDouble() // Assuming height is already in meters
        var weight = intent.getStringExtra("weight")!!.toDouble()
        var click = intent.getStringExtra("click")

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
            finish()
        }


        // Proceed with BMI calculation using height and weight
        var bmiLevel = (weight / (height * height))

        if (click?.toInt() == 0) {

            binding.bmiValue.text = String.format("%.2f", bmiLevel)
            when (bmiLevel) {
                in Double.MIN_VALUE..18.5 ->
                    binding.bmiInLevel.text = "Underweight"

                in 18.5..24.9 -> binding.bmiInLevel.text = "Normal "
                in 25.0..29.9 -> binding.bmiInLevel.text = "Overweight"
                else -> binding.bmiInLevel.text = "Obesity"
            }
            if (bmiLevel < 18.5) {
                binding.bmiInLevel.setTextColor(Color.parseColor("#E1CF03"))
                binding.bmiValue.setTextColor(Color.parseColor("#E1CF03"))
            } else if (bmiLevel >= 18.5 && bmiLevel <= 24.9) {
                binding.bmiInLevel.setTextColor(Color.parseColor("#087112"))
                binding.bmiValue.setTextColor(Color.parseColor("#087112"))

            } else if (bmiLevel >= 25 && bmiLevel <= 29.9) {
                binding.bmiInLevel.setTextColor(Color.parseColor("#FF3700"))
                binding.bmiValue.setTextColor(Color.parseColor("#FF3700"))

            } else if (bmiLevel >= 30.0) {
                binding.bmiInLevel.setTextColor(Color.parseColor("#FC0A01"))
                binding.bmiValue.setTextColor(Color.parseColor("#FC0A01"))

            }

//            Database

            CoroutineScope(Dispatchers.IO).launch {
          db.BmiDao().insertData(BmiTable("Male", "$height", "$weight",Date()))
            }


            binding.genderResult.text = "Gender           Male"
            binding.ageResult.text = "Age           $age"
            binding.heightResult.text = "Height           $height cm"
            binding.weightResult.text = "Weight           $weight kg"


        } else if (click?.toInt() == 1) {

            binding.bmiValue.text = String.format("%.2f", bmiLevel)
            when (bmiLevel) {
                in Double.MIN_VALUE..18.5 -> binding.bmiInLevel.text = "Underweight"
                in 18.5..24.9 -> binding.bmiInLevel.text = "Normal"
                in 25.0..29.9 -> binding.bmiInLevel.text = "Overweight"
                else -> binding.bmiInLevel.text = "Obesity"
            }

//            //Database

//            val calender = Calendar.getInstance()
//            val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calender)

            CoroutineScope(Dispatchers.IO).launch {

                db.BmiDao().insertData(BmiTable("Female", "$height", "$weight", Date()))
            }


            binding.genderResult.text = "Gender           Female"
            binding.ageResult.text = "Age           $age"
            binding.heightResult.text = "Height           $height m"
            binding.weightResult.text = "Weight           $weight kg"

        }
    }
}