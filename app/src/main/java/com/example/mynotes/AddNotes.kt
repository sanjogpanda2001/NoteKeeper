package com.example.mynotes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.lang.Exception

class AddNotes : AppCompatActivity() {
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
      try{
        var bundle: Bundle = intent.extras!!
        id = bundle.getInt("ID")
        if (id != 0) {
            editTextTextPersonName.setText(bundle.getString("name"))
            editTextTextPersonName2.setText(bundle.getString("des"))
        }
      }catch (ex:Exception){}
    }

    fun buAdd(view:View){
        var dbManager=DbManager(this)
     var value=ContentValues()
        value.put("Title",editTextTextPersonName.text.toString())
        value.put("Description",editTextTextPersonName2.text.toString())

        if (id==0){
     val ID=dbManager.Insert(value)
        if (ID>0){
            Toast.makeText(this,"note added",Toast.LENGTH_SHORT)
        }else{
            Toast.makeText(this,"note not added",Toast.LENGTH_SHORT)
        }}
        else{
            var selectionargs= arrayOf(id.toString())
            var ID=dbManager.Update(value,"ID=?",selectionargs)
            if (ID>0){
                Toast.makeText(this,"note added",Toast.LENGTH_SHORT)
            }else{
                Toast.makeText(this,"note not added",Toast.LENGTH_SHORT)
            }
        }

finish()
    }
}