package com.example.bmi.Activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log

import android.widget.Toast
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

        val age = intent.getStringExtra("age")
        val heightStr = intent.getStringExtra("height")
        val weightStr = intent.getStringExtra("weight")
        val click = intent.getStringExtra("click")


        if (heightStr != null && weightStr != null) {
            val height = heightStr.toDouble()
            val weight = weightStr.toDouble()
            val bmiLevel = weight / (height * height)
            val BmiString = String.format("%.2f", bmiLevel)
            Log.d("BMILEVEL", "height : ${height} , weight : ${weight} , bmi level : ${BmiString}")

            if (height != null && weight != null) {
                val bmiLevel = weight / (height * height)
                binding.bmiValue.text = String.format("%.2f", bmiLevel)
                when (bmiLevel) {
                    in Double.MIN_VALUE..18.5 -> binding.bmiInLevel.text = "Underweight"
                    in 18.5..24.9 -> binding.bmiInLevel.text = "Normal"
                    in 25.0..29.9 -> binding.bmiInLevel.text = "Overweight"
                    else -> binding.bmiInLevel.text = "Obesity"
                }

                setBmiTextColor(bmiLevel)

                CoroutineScope(Dispatchers.IO).launch {
                    val gender = if (click?.toInt() == 0) "Male" else "Female"
                    db.BmiDao().insertData(BmiTable(gender, "$height", "$weight", Date(),BmiString))
                }

                val genderResult = if (click?.toInt() == 0) "Male" else "Female"
                binding.genderResult.text = "Gender           $genderResult"
                binding.ageResult.text = "Age           $age"
                binding.heightResult.text =
                    "Height           ${height * 100} cm"
                binding.weightResult.text = "Weight           $weight kg"
            } else {
                Toast.makeText(this, "Invalid height or weight", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Height or weight data is missing", Toast.LENGTH_SHORT).show()
        }
        binding.backBtn.setOnClickListener {
            super.onBackPressed()
            finish()
        }
    }

    private fun setBmiTextColor(bmiLevel: Double) {
        when {
            bmiLevel < 18.5 -> {
                binding.bmiInLevel.setTextColor(Color.parseColor("#E1CF03"))
                binding.bmiValue.setTextColor(Color.parseColor("#E1CF03"))
            }

            bmiLevel in 18.5..24.9 -> {
                binding.bmiInLevel.setTextColor(Color.parseColor("#087112"))
                binding.bmiValue.setTextColor(Color.parseColor("#087112"))
            }

            bmiLevel in 25.0..29.9 -> {
                binding.bmiInLevel.setTextColor(Color.parseColor("#FF3700"))
                binding.bmiValue.setTextColor(Color.parseColor("#FF3700"))
            }

            else -> {
                binding.bmiInLevel.setTextColor(Color.parseColor("#FC0A01"))
                binding.bmiValue.setTextColor(Color.parseColor("#FC0A01"))
            }
        }
    }
}