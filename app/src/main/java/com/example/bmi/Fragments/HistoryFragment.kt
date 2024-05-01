package com.example.bmi.Fragments

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmi.R
import com.example.bmi.RecyclerView.BmiAdapter
import com.example.bmi.RoomDb.BmiDatabase
import com.example.bmi.RoomDb.BmiTable
import com.example.bmi.databinding.FragmentHistoryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class HistoryFragment : Fragment() {
lateinit var binding: FragmentHistoryBinding
lateinit var db:BmiDatabase
lateinit var dataList: List<BmiTable>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db= BmiDatabase.getDatabase(requireContext())


         dataList = db.BmiDao().getAllData()


        binding.recyclerView.adapter = BmiAdapter(requireContext().applicationContext , dataList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
}