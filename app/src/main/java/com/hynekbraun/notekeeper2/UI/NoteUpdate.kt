package com.hynekbraun.notekeeper2.UI

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hynekbraun.notekeeper2.R
import com.hynekbraun.notekeeper2.Util.MainViewModel
import com.hynekbraun.notekeeper2.Util.NoteEntity
import com.hynekbraun.notekeeper2.databinding.ActivityNoteUpdateBinding
import java.text.SimpleDateFormat
import java.util.*

class NoteUpdate : AppCompatActivity() {

    private lateinit var _binding: ActivityNoteUpdateBinding
    val binding get() = _binding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val currentNote: NoteEntity = fetchExtras()

        setUpValues(currentNote)

        binding.ivNoteUpdateSave.setOnClickListener {
            val header = binding.etNoteUpdateHeader.text.toString()
            val description = binding.etNoteUpdateDescription.text.toString()
            val date = getCurrentDateTime()
            val color = (binding.tvNoteUdpateColorChange.background as ColorDrawable).color


            if (header.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in the header and note", Toast.LENGTH_LONG).show()
            } else {
                val updatedNote: NoteEntity =
                    NoteEntity(currentNote.id, header, description, date, color, currentNote.isDone)
                viewModel.updateNote(updatedNote)
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvNoteUdpateColorChange.setOnClickListener {
            val updateColorDialog = Dialog(this, R.style.Theme_Dialog)
            updateColorDialog.setCancelable(true)
            updateColorDialog.setContentView(R.layout.dialog_pick_color)

            updateColorDialog.findViewById<View>(R.id.color_pick_blue).setOnClickListener {
                binding.tvNoteUdpateColorChange.setBackgroundColor(getColor(R.color.background_blue))
                binding.llNoteUpdateHeaderBackground.setBackgroundColor(getColor(R.color.background_blue))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_red).setOnClickListener {
                binding.tvNoteUdpateColorChange.setBackgroundColor(getColor(R.color.background_red))
                binding.llNoteUpdateHeaderBackground.setBackgroundColor(getColor(R.color.background_red))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_yellow).setOnClickListener {
                binding.tvNoteUdpateColorChange.setBackgroundColor(getColor(R.color.background_yellow))
                binding.llNoteUpdateHeaderBackground.setBackgroundColor(getColor(R.color.background_yellow))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_green).setOnClickListener {
                binding.tvNoteUdpateColorChange.setBackgroundColor(getColor(R.color.background_green))
                binding.llNoteUpdateHeaderBackground.setBackgroundColor(getColor(R.color.background_green))
                updateColorDialog.dismiss()
            }
            updateColorDialog.show()

        }

    }

    private fun setUpValues(currentNote: NoteEntity) {
        binding.llNoteUpdateHeaderBackground.setBackgroundColor(currentNote.color)
        binding.tvNoteUdpateColorChange.setBackgroundColor(currentNote.color)
        binding.etNoteUpdateHeader.setText(currentNote.header)
        binding.etNoteUpdateDescription.setText(currentNote.description)
        binding.tvNoteUpdateDate.text = getCurrentFormatedDate(getCurrentDateTime())

    }

    private fun fetchExtras(): NoteEntity {
        val id: Long = intent.getLongExtra("id", 0)
        val header = intent.getStringExtra("header")
        val description = intent.getStringExtra("description")
        val color = intent.getIntExtra("color", R.color.background_blue)
        val isDone = intent.getBooleanExtra("isDone", false)
        return NoteEntity(id, header!!, description!!, getCurrentDateTime(), color, isDone)
    }

    private fun getCurrentDateTime(): Date {
        val date = Calendar.getInstance().time
        return date
    }
    private fun getCurrentFormatedDate(date: Date): String{
        val formatter = SimpleDateFormat("MM-dd HH:mm")
        return formatter.format(date)
    }

}