package com.hynekbraun.notekeeper2.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hynekbraun.notekeeper2.R
import com.hynekbraun.notekeeper2.Util.MainViewModel
import com.hynekbraun.notekeeper2.Util.NoteAdapter
import com.hynekbraun.notekeeper2.Util.NoteEntity
import com.hynekbraun.notekeeper2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteAdapter.OnItemClickListener
//    ,androidx.appcompat.widget.SearchView.OnQueryTextListener
{

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    val adapter: NoteAdapter by lazy { NoteAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.mainToolbar
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter


        viewModel.allNotes.observe(this){
            adapter.getNotes(it as ArrayList<NoteEntity>)

        }

        binding.fbMain.setOnClickListener {
            val intent = Intent(this, Note::class.java)
            startActivity(intent)

        }


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_searchview, menu)
//
//        val search = menu?.findItem(R.id.mainSearchBar)
//        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
//        return true
//    }
//
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if (query != null) {
//            searchDatabase(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(query: String?): Boolean {
//        if (query != null) {
//            searchDatabase(query)
//        }
//        return true
//
//    }

//    private fun searchDatabase(query: String) {
//        val searchQuery = "%$query%"
//
//        viewModel.searchNote(searchQuery).observe(this, {
//            it.let {
//                adapter.getNotes(it as ArrayList<NoteEntity>)
//
//            }
//
//        })
//    }




    override fun onItemClick(position: Int) {
        val currentItem = viewModel.allNotes.value!![position]
        val intent2 = Intent(this, NoteUpdate::class.java)
        intent2.putExtra("id", currentItem.id)
        intent2.putExtra("header", currentItem.header)
        intent2.putExtra("description", currentItem.description)
        intent2.putExtra("color", currentItem.color)
        intent2.putExtra("date", currentItem.date)
        intent2.putExtra("isDone", currentItem.isDone)
        startActivity(intent2)

    }
}