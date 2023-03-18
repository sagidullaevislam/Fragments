package com.example.demoroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.room.Dao
import com.example.demoroomapp.StudentDataBase.Companion.getInstance
import com.example.demoroomapp.adapters.StudentAdapter
import com.example.demoroomapp.databinding.ActivityMainBinding
import java.util.Currency.getInstance

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = StudentAdapter()
    private lateinit var dao: StudentsDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = StudentDataBase.getInstance(this).getStudentDao()

        binding.apply {
            recycleView.adapter = adapter
        }
        lifecycleScope.launchWhenResumed {
            adapter.models = dao.getListOfStudents()
        }

        binding.btnPlus.setOnClickListener {
            intent = Intent(this, AddActivity::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }
        adapter.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("id", it.id)
            intent.putExtra("name", it.name)
            intent.putExtra("surname", it.surname)
            startActivity(intent)
        }


        binding.etSearch.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                lifecycleScope.launchWhenResumed {
                    adapter.models = dao.searchStudentByName(it.toString())
                }
            } else {
                lifecycleScope.launchWhenResumed {
                    adapter.models = dao.getListOfStudents()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launchWhenResumed {
            adapter.models = dao.getListOfStudents()
        }
    }
}