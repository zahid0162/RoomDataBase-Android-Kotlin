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

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<Employee>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        println(currentItem.toString())
        holder.itemView.findViewById<TextView>(R.id.id_txt).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.firstName_txt).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.lastName_txt).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.age_txt).text = currentItem.salary.toString()
        holder.itemView.findViewById<TextView>(R.id.email_txt).text=currentItem.email
        holder.itemView.findViewById<TextView>(R.id.dep_id).text=currentItem.departmentId.toString()


        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener {

            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem) // <- Pass object to Update Fragment
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<Employee>) {
        this.userList = user
        notifyDataSetChanged()
    }
}