package com.example.mynotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfNotes=ArrayList<noteStructure>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 //dummy data
       listOfNotes.add(noteStructure(1,"oct","basics of android"))
        //listOfNotes.add(noteStructure(2,"nov","any opensource project"))
        //listOfNotes.add(noteStructure(3,"oct","dedicate to organisation"))

       var myNotesAdapter= MyNotesAdapter(this,listOfNotes)
        lvNotes.adapter=myNotesAdapter

       LoadQuery("%")
    }

    override fun onResume() {
        LoadQuery("%")
        super.onResume()
    }

    fun LoadQuery(title:String){
        var dbManager1=DbManager(this )
        val projections= arrayOf("ID","Title","Description")
        val selectionSArgs= arrayOf("%")
        val cursor=dbManager1.Query(null,"Title like ?",selectionSArgs,"Title")
        listOfNotes.clear()
        if(cursor.moveToFirst()){
            do {
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))
                listOfNotes.add(noteStructure(ID,Title,Description))
            }while (cursor.moveToNext())
        }
        var myNotesAdapter= MyNotesAdapter(this,listOfNotes)
        lvNotes.adapter=myNotesAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val sv= menu?.findItem(R.id.app_bar_search)?.actionView as SearchView
        val sm=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                Toast.makeText(applicationContext,query,Toast.LENGTH_LONG).show()
                LoadQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
              //  TODO("Not yet implemented")
return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addNote->{
                var intent= Intent(this,AddNotes::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner  class MyNotesAdapter: BaseAdapter {
        var listOfNotesAdapter=ArrayList<noteStructure>()
        var context:Context?=null

        constructor(context:Context,listOfNotesAdapter:ArrayList<noteStructure>):super(){
            this.listOfNotesAdapter=listOfNotesAdapter
            this.context=context
        }

        override fun getCount(): Int {
            //TODO("Not yet implemented")
            return listOfNotesAdapter.size
        }

        override fun getItem(position: Int): Any {
            //TODO("Not yet implemented")
            return listOfNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            //TODO("Not yet implemented")
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
           // TODO("Not yet implemented")
            var myView=layoutInflater.inflate(R.layout.ticket,null)
            var myNote=listOfNotesAdapter[position]
            myView.tvTitle.text=myNote.noteName
            myView.tvDes.text=myNote.noteDes
            myView.imageButton.setOnClickListener(View.OnClickListener{
                var dbm=DbManager(this.context!!)
                val selectionArgs= arrayOf(myNote.noteID.toString())
                dbm.Delete("ID=?",selectionArgs )
                LoadQuery("%")
            })
            myView.imageButton2.setOnClickListener(View.OnClickListener{
               GoToUpdate(myNote)
            })


            return  myView
        }

    }

    fun GoToUpdate(note:noteStructure){
        var intent=Intent(this,AddNotes::class.java)
        intent.putExtra("ID",note.noteID)
        intent.putExtra("name",note.noteName)
        intent.putExtra("des",note.noteDes)
        startActivity(intent)
    }

}

