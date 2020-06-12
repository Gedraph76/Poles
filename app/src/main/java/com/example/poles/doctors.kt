package com.example.poles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.doctors.*

class doctors : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctors)


        consult.setOnClickListener {
            showMessage("Success","Dr.James will reach you")
        }
        consult2.setOnClickListener {
            showMessage("Success","Dr.Ann will reach you")
        }
        consult3.setOnClickListener {
            showMessage("Success","Dr.Michael will reach you")
        }

    }
    private fun showMessage(title: String, message: String){
        val dialogBox = android.app.AlertDialog.Builder(this)
        dialogBox.setTitle(title)
        dialogBox.setMessage(message)
        dialogBox.setPositiveButton("OK", { dialog, which -> dialog.dismiss() })
        dialogBox.create().show()
    }
}
