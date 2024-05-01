package com.example.bmi.Fragments

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bmi.databinding.FragmentHomeBinding
import com.example.bmi.Activities.resultActivity

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var dialog: Dialog
    var click = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LogoShow()
        inputData()

    }
    private fun inputData() {

        val age = binding.ageInput.text
        val height = binding.heightInput.text
        val weight = binding.weightInput.text

        binding.calculate.setOnClickListener {
            if (age.isNotBlank()&& height.isNotBlank() && weight.isNotBlank()) {

                var intent = Intent(context, resultActivity::class.java)
                intent.putExtra("click",click.toString())
                intent.putExtra("age", age.toString())
                intent.putExtra("height", height.toString())
                intent.putExtra("weight", weight.toString())
                if (context != null) {
                    context?.startActivity(intent)
                } else {
                    // Handle the case where context is null (e.g., log an error)
                    Toast.makeText(context, "Context is null, cannot launch ResultActivity", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(context, "Blank Fills are not allowed", Toast.LENGTH_SHORT).show()

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