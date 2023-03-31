package com.example.roomdatabase

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.materialswitch.MaterialSwitch

class RVAdapter(
    private val arrayList: ArrayList<Student>,
    private val context: Context,
    private val mainActivityViewModel: MainActivityViewModel,
    private val mainActivity: MainActivity,
) : RecyclerView.Adapter<RVAdapter.MyAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        return MyAdapter(
            view = LayoutInflater.from(parent.context).inflate(R.layout.card_design, null, false)
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        val student = arrayList[position]
        holder.tvId.text = student.id.toString()
        holder.tvName.text = student.name
        holder.tvRollNo.text = student.rollNo.toString()
        holder.tvPass.text = student.pass.toString()
        holder.cardView.setOnClickListener {
            val dialog: Dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_design)
            dialog.setCancelable(true)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val tvIdUpdate: TextView = dialog.findViewById(R.id.tvIdUpdate)
            val etNameUpdate: EditText = dialog.findViewById(R.id.etNameUpdate)
            val etRollNoUpdate: EditText = dialog.findViewById(R.id.etRollNoUpdate)
            val update: Button = dialog.findViewById(R.id.update)
            val switchButtonUpdate: SwitchCompat = dialog.findViewById(R.id.switchButtonUpdate)

            tvIdUpdate.text = student.id.toString()
            etNameUpdate.setText(student.name)
            etRollNoUpdate.setText(student.rollNo.toString())
            if (student.pass) switchButtonUpdate.isChecked = true


            var passUpdate = switchButtonUpdate.isChecked
            switchButtonUpdate.setOnClickListener {
                passUpdate = switchButtonUpdate.isChecked
            }

            update.setOnClickListener {
                val nameUpdate = etNameUpdate.text.toString()
                val rollNoUpdate = etRollNoUpdate.text.toString().toInt()
                mainActivityViewModel.updateStudent(
                    context,

                    Student(student.id, nameUpdate, rollNoUpdate, passUpdate)
                )
                mainActivity.listData()
                dialog.dismiss()
            }
            dialog.show()


        }
        holder.cardView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialog.setTitle("DELETE!").setCancelable(false).setMessage("Sure you want to delete the student ${student.name}?")
                    .setNegativeButton("NO", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                        }

                    }).setPositiveButton("YES", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            mainActivityViewModel.deleteStudent(context, student)
                            mainActivity.listData()
                            Toast.makeText(
                                context, "Student ${student.name} is deleted", Toast.LENGTH_SHORT
                            ).show()
                        }

                    }).show()
                return true
            }

        })
    }

    class MyAdapter(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvRollNo: TextView = view.findViewById(R.id.tvRollNo)
        val tvPass: TextView = view.findViewById(R.id.tvPass)
        val tvId: TextView = view.findViewById(R.id.tvId)
        val cardView: MaterialCardView = view.findViewById(R.id.cv1)
    }

}