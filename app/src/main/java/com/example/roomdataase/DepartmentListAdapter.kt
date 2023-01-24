package com.example.roomdataase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DepartmentListAdapter(
    var departments: MutableList<Department>,
    private val onItemClick: (Department) -> Unit
) : RecyclerView.Adapter<DepartmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        return DepartmentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_department,
                parent,
                false
            )
        ){
            onItemClick(departments[it])
        }
    }

    override fun getItemCount(): Int {
        return departments.size
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val currentItem = departments[position]

        holder.itemView.findViewById<TextView>(R.id.dep_id).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.dep_name).text = currentItem.name

    }

    fun setData(user: List<Department>) {
        this.departments = user as MutableList<Department>
        notifyDataSetChanged()
    }

}
