package com.example.demoroomapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroomapp.R
import com.example.demoroomapp.StudentData
import com.example.demoroomapp.databinding.ItemStudentBinding

class StudentAdapter:RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: StudentData) {
            binding.tvFio.text = "${student.name} ${student.surname}"
            binding.tvId.text = student.id.toString()


            binding.root.setOnClickListener {
                setOnClickListener?.invoke(student)
            }
        }
    }

    private var setOnClickListener: ((StudentData)-> Unit)? = null
    fun setOnClickListener(block:((StudentData)-> Unit)){
        setOnClickListener = block
    }

    var models = listOf<StudentData>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        val binding = ItemStudentBinding.bind(view)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
     holder.bind(models[position])
    }
}
