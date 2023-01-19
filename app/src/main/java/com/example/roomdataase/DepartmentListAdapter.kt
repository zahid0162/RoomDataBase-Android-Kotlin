package com.example.roomdataase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdataase.model.User

class DepartmentListAdapter: RecyclerView.Adapter<DepartmentListAdapter.MyViewHolder>() {

    private var userList = emptyList<Department>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row_department,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.findViewById<TextView>(R.id.dep_id).text=currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.dep_name).text=currentItem.name

    }

    fun setData(user: List<Department>) {
        this.userList = user
        notifyDataSetChanged()
    }
}