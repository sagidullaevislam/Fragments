package com.example.demoroomapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.demoroomapp.databinding.ActivityAddBinding

@Suppress("DEPRECATION")
class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var dao: StudentsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isEdit = intent.getBooleanExtra("isEdit", false)
        val studentId = intent.getIntExtra("id", 0)
        val studentName = intent.getStringExtra("name")
        val studentSurname = intent.getStringExtra("surname")

        dao = StudentDataBase.getInstance(this).getStudentDao()

        if (isEdit) {
            binding.btnAdd.text = "Edit"
            binding.etName.setText(studentName)
            binding.etSurname.setText(studentSurname)
        } else {
            binding.btnAdd.text = "Add"
        }


        binding.icBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDefault.setOnClickListener {
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            pref.edit().putString("language", "Ru").apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty()) {
                lifecycleScope.launchWhenResumed {
                    dao.addStudent(StudentData(0, name, surname))
                }
            } else {
                Toast.makeText(this,"Magliwmatlardi kiritin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}