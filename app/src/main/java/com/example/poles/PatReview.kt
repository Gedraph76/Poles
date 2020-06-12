package com.example.poles

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.pat_review.*

class PatReview : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pat_review)

        //        add a new user
        imgadduser.setOnClickListener {
            startActivity(Intent(this, patients::class.java))
        }
        imgrefresh.setOnClickListener {

        }



////        pull data from the database and get it displayed
////        open the database that was already created in the main activity
//        val db : SQLiteDatabase = openOrCreateDatabase("appdb" , Context.MODE_PRIVATE,null)
//
////        pull data from the users table
//        val sql = "SELECT * FROM Pats"
//
////        create user list[array]
//
//        val patient : ArrayList<pat> = ArrayList()
//
//        val cursor = db.rawQuery(sql , null)
//
//        if (cursor.count == 0){
//            showMessage("Sorry", "No doctors available at the momment.")
//        }else{
//            while (cursor.moveToNext()) {
//                patient.add(
//                    pat(
//                        cursor.getString(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getString(5),
//                        cursor.getString(6),
//                        cursor.getString(7)
//                    )
//                )
//                userlist.adapter = CustomAdapter(this,patient)
//            }
//        }



//        create an array that will inflate the layout
        val users: ArrayList<pat> = ArrayList()
//        val cursor = db.rawQuery("SELECT * FROM users", null)
        val cursor = dbHelper.allData
//            check if there are any records from the database
        if (cursor.count == 0 ) {
            showMessage("No records ", "Sorry no records were found !!", this)
        } else {
            refresh()
        }
    }

    fun refresh(){
        val users: ArrayList<pat> = ArrayList()
//        val cursor = db.rawQuery("SELECT * FROM users", null)
        val cursor = dbHelper.allData
//            check if there are any records from the database
        if (cursor.count == 0) {
            showMessage("No records ", "Sorry no records were found !!", this)
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
            userlist.adapter = CustomAdapter(this, users)
        }
    }
    fun showMessage(title: String, message:String, context:Context){
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener{ dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }
}



