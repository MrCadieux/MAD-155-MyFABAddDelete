package com.example.myfab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var listItems = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView
    lateinit var fabAdd: FloatingActionButton
    lateinit var fabDelete: FloatingActionButton
    lateinit var undoOnClickListener: View.OnClickListener
    lateinit var redoOnClickListener: View.OnClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv1)

        adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, listItems)

        listView.adapter = adapter
        fabAdd = findViewById(R.id.FABadd)
        fabDelete = findViewById(R.id.FABdelete)

        fabAdd.setOnClickListener {
            addListItem()
            Snackbar.make(it, "Added an Item", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", undoOnClickListener)
                    .show()
        }

        fabDelete.setOnClickListener {
            if (listItems.size==0){
                Snackbar.make(it, "No Items to Delete", Snackbar.LENGTH_LONG).setAction("Add Item", redoOnClickListener).show()
            }
            else {
                deleteListItem()
                Snackbar.make(it, "Deleted an Item", Snackbar.LENGTH_LONG)
                    .setAction("REDO", redoOnClickListener)
                    .show()
            }
        }

        undoOnClickListener = View.OnClickListener {
            listItems.removeAt(listItems.size-1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item removed", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }

        redoOnClickListener = View.OnClickListener {
            listItems.add("Item 2")
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item added", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }


    }

    private fun deleteListItem() {
        listItems.removeAt(listItems.size-1)
        adapter?.notifyDataSetChanged()
    }

    private fun addListItem() {
        listItems.add("Item 1")
        adapter?.notifyDataSetChanged()
    }
}