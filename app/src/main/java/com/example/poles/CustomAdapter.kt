package com.example.poles

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_dilog_form.view.*

class CustomAdapter(var context: Context, var data: ArrayList<pat>): BaseAdapter() {
    internal var dbHelper = DatabaseHelper(context)
    private class ViewHolder(row: View?){
        var namePat: TextView
        var gender:TextView
        var agePat:TextView
        var locationPat:TextView
        var idNumber:TextView
        var phonePat:TextView
        var emailPat:TextView
        var passowrdPat:TextView
        var imgdelete: ImageView
        var imgupdate:ImageView


        init {

            this.namePat = row?.findViewById(R.id.tvNamePat) as TextView
            this.gender = row?.findViewById(R.id.tvGender) as TextView
            this.agePat = row?.findViewById(R.id.tvAgePat) as TextView
            this.locationPat = row?.findViewById(R.id.tvLocationPat) as TextView
            this.idNumber = row?.findViewById(R.id.tvIdNumber) as TextView
            this.phonePat = row?.findViewById(R.id.tvPhonePat) as TextView
            this.emailPat = row?.findViewById(R.id.tvEmailPat) as TextView
            this.passowrdPat = row?.findViewById(R.id.tvPasswordPat) as TextView
            this.imgdelete = row?.findViewById(R.id.imgdelete) as ImageView
            this.imgupdate = row?.findViewById(R.id.imgupdate) as ImageView


        }
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view:View?
        val viewHolder:ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.pat_list,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val patient:pat = getItem(position) as pat
        viewHolder.namePat.text = patient.namePat
        viewHolder.gender.text = patient.gender
        viewHolder.agePat.text = patient.agePat
        viewHolder.locationPat.text = patient.locationPat
        viewHolder.idNumber.text = patient.idNumber
        viewHolder.phonePat.text = patient.phonePat
        viewHolder.emailPat.text = patient.emailPat

        val NamesPat = patient.namePat
        val Genders = patient.gender
        val Agepats = patient.agePat
        val LocationPat = patient.locationPat
        val IdNumber = patient.idNumber
        val PhonePat = patient.phonePat
        val EmailPat = patient.emailPat
        val PasswordPat = patient.passwordPat
        val id:String

        viewHolder.imgupdate.setOnClickListener {
            var dialogBuilder = AlertDialog.Builder(context)
            var myInflater = LayoutInflater.from(context)
            var dialogview = myInflater.inflate(R.layout.alert_dilog_form,parent,false)
            dialogBuilder.setView(dialogview)

//            populate the edittexts with data

            dialogview.detNamePat.setText(NamesPat)
            dialogview.detGender.setText(Genders)
            dialogview.detAgePat.setText(Agepats)
            dialogview.detLocationPat.setText(LocationPat)
            dialogview.detIdNumber.setText(IdNumber)
            dialogview.detPhonePat.setText(PhonePat)
            dialogview.detEmailPat.setText(EmailPat)
            dialogview.detPasswordPat.setText(PasswordPat)

            dialogBuilder.setTitle("Edit")
            dialogBuilder.setMessage("Editing ${NamesPat}?")
            dialogBuilder.setPositiveButton("Edit", {dialog, which ->

                val uptName = dialogview.detNamePat.text.toString()
                val uptGender = dialogview.detGender.text.toString()
                val uptAge = dialogview.detAgePat.text.toString()
                val uptLocation = dialogview.detLocationPat.text.toString()
                val uptIdNumber = dialogview.detIdNumber.text.toString()
                val uptPhone = dialogview.detPhonePat.text.toString()
                val uptEmail = dialogview.detEmailPat.text.toString()
                val uptPassword = dialogview.detPasswordPat.text.toString()

                val cursor = dbHelper.allData

                while (cursor.moveToNext()) {
                    if (NamesPat == cursor.getString(1)){
                        dbHelper.updateData(
                            uptName,
                            uptGender,
                            uptAge,
                           uptLocation,
                            uptIdNumber,
                            uptPhone,
                            uptEmail,
                            uptPassword
                        )
                    }

                }
                this.notifyDataSetChanged()

                Toast.makeText(context, "${ NamesPat} Updated successfully", Toast.LENGTH_SHORT).show()

            })

            dialogBuilder.setNegativeButton("Cancel", {dialog, which -> dialog.dismiss() })
            dialogBuilder.create().show()

        }

        viewHolder.imgdelete.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Delete")
            dialogBuilder.setMessage("Confirm Delete item ${NamesPat}?")

            dialogBuilder.setPositiveButton("Delete", {dialog, which ->

                dbHelper.deleteData(NamesPat)
                val users: ArrayList<pat> = ArrayList()

                val cursor = dbHelper.allData

//            check if there are any records from the database
                if (cursor.count == 0) {
                    showMessage("No records ", "Sorry no records were found !!", context)
                } else {
                    while (cursor.moveToNext()) {
                        users.add(
                            pat(
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8)
                            )
                        )

                    }
                    this.notifyDataSetChanged()
                }

            })
            dialogBuilder.setNegativeButton("Cancel", {dialog, which -> dialog.dismiss() })
            dialogBuilder.create().show()

        }



        return view as View
    }

    fun showMessage(title: String, message:String, context:Context){
        val alertDialog: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(context)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", { dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }









}