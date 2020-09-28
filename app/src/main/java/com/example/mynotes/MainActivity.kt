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
        listOfNotes.add(noteStructure(2,"nov","any opensource project"))
        listOfNotes.add(noteStructure(3,"oct","dedicate to organisation"))

        var myNotesAdapter= MyNotesAdapter(listOfNotes)
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
        constructor(listOfNotesAdapter:ArrayList<noteStructure>):super(){
            this.listOfNotesAdapter=listOfNotesAdapter
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

            return  myView
        }

    }

}

