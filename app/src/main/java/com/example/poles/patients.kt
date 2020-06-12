package com.example.poles

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.doctors.*
import kotlinx.android.synthetic.main.patients.*

class patients : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patients)

        //        create an instance of a database
        //val db : SQLiteDatabase = openOrCreateDatabase("Polesdb" , Context.MODE_PRIVATE,null)

        //db.execSQL("CREATE TABLE IF NOT EXISTS jobs(namePat VARCHAR, gender VARCHAR, locationPat VARCHAR," +
             //  "idNumber VARCHAR,phonePat VARCHAR,emailPat VARCHAR, passwordPat VARCHAR)")

        //view password
        img_show_pat.setOnClickListener {
            showPassword()
        }
        //add user
        btn_signup_Pat.setOnClickListener {
            val namePat = FullNamesPat.text.trim().toString()
            val gender = Gender.text.trim().toString()
            val agePat = AgePat.text.trim().toString()
            val locationPat = LocationPat.text.trim().toString()
            val idNumber = Id_number.text.trim().toString()
            val phonePat = PhonePat.text.trim().toString()
            val emailPat = EmailPat.text.trim().toString()
            val passwordPat = PasswordPat.text.trim().toString()

//            check if users entered all field
            if (namePat.isEmpty() or gender.isEmpty() or agePat.isEmpty() or locationPat.isEmpty()
                or idNumber.isEmpty() or phonePat.isEmpty() or emailPat.isEmpty() or passwordPat.isEmpty()) {
                showMessage("Empty fields or field", "Please fill all the inputs")
            } else {
             // db.execSQL("INSERT INTO Pats VALUES ('"+namePat+"','"+gender+"','"+agePat+"','"+locationPat+"','"+idNumber+"','"+phonePat+"','"+emailPat+"','"+passwordPat+"')")
                dbHelper.insertData(namePat,gender, agePat, locationPat, idNumber, phonePat, emailPat, passwordPat)
                showMessage("Success", " User data of$namePat has been saved successfully")
                clearEditText()
                startActivity(Intent(this,doctors::class.java))

            }

        }

    }

    private fun showMessage(title: String, message: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", { dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }


    private fun clearEditText() {
        FullNamesPat.setText("")
        Gender.setText("")
        AgePat.setText("")
        LocationPat.setText("")
        Id_number.setText("")
        PhonePat.setText("")
        EmailPat.setText("")
        PasswordPat.setText("")
    }
    private fun showPassword(){
        if (img_show_pat.tag.toString().equals("Show")){
            PasswordPat.transformationMethod = HideReturnsTransformationMethod.getInstance()
            img_show_pat.tag = "Hide"
        }else{
           PasswordPat.transformationMethod = PasswordTransformationMethod.getInstance()
            PasswordPat.tag = "Show"
        }
    }
}





