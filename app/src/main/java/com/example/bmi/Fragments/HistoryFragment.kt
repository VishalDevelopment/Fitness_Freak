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

class HistoryFragment : Fragment() {
lateinit var binding: FragmentHistoryBinding
lateinit var db:BmiDatabase
lateinit var dataList: ArrayList<BmiTable>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = BmiDatabase.getDao(requireContext().applicationContext)

//        GlobalScope.launch{
//            dataList = db.BmiDao().getAllData()
//        }
        dataList = arrayListOf()
        dataList.add(BmiTable(0,"male","1.7","70.4"))
        dataList.add(BmiTable(1,"male","1.7","70.4"))
        dataList.add(BmiTable(2,"male","1.7","70.4"))
        dataList.add(BmiTable(3,"male","1.7","70.4"))
        dataList.add(BmiTable(4,"male","1.7","70.4"))
        dataList.add(BmiTable(5,"male","1.7","70.4"))


        binding.recyclerView.adapter = BmiAdapter(requireContext().applicationContext , dataList)


    }
}