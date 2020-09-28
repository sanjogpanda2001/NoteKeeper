package com.example.mynotes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun buAdd(view:View){
        var dbManager=DbManager(this)
     var value=ContentValues()
        value.put("Title",editTextTextPersonName.text.toString())
        value.put("Description",editTextTextPersonName2.text.toString())
     val ID=dbManager.Insert(value)
        if (ID>0){
            Toast.makeText(this,"note added",Toast.LENGTH_SHORT)
        }else{
            Toast.makeText(this,"note not added",Toast.LENGTH_SHORT)
        }

    }
}