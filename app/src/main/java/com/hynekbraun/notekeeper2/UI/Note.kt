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
import com.hynekbraun.notekeeper2.databinding.ActivityNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class Note : AppCompatActivity() {

    private lateinit var _binding: ActivityNoteBinding
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val currentDate = getCurrentDateTime()
        binding.tvNoteDate.text = getFormatedDate(currentDate)

        binding.ivNoteSave.setOnClickListener {
            val header = binding.etNoteHeader.text.toString()
            val description = binding.etNoteDescription.text.toString()
            val color = (binding.tvNoteColorChange.background as ColorDrawable).color
            if (header.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in the header and note", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addNote(NoteEntity(0, header, description, currentDate, color, false))
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
        binding.tvNoteColorChange.setOnClickListener {
            val updateColorDialog = Dialog(this, R.style.Theme_Dialog)
            updateColorDialog.setCancelable(true)
            updateColorDialog.setContentView(R.layout.dialog_pick_color)

            updateColorDialog.findViewById<View>(R.id.color_pick_blue).setOnClickListener {
                binding.tvNoteColorChange.setBackgroundColor(getColor(R.color.background_blue))
                binding.llNoteHeaderBackground.setBackgroundColor(getColor(R.color.background_blue))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_red).setOnClickListener {
                binding.tvNoteColorChange.setBackgroundColor(getColor(R.color.background_red))
                binding.llNoteHeaderBackground.setBackgroundColor(getColor(R.color.background_red))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_yellow).setOnClickListener {
                binding.tvNoteColorChange.setBackgroundColor(getColor(R.color.background_yellow))
                binding.llNoteHeaderBackground.setBackgroundColor(getColor(R.color.background_yellow))
                updateColorDialog.dismiss()
            }
            updateColorDialog.findViewById<View>(R.id.color_pick_green).setOnClickListener {
                binding.tvNoteColorChange.setBackgroundColor(getColor(R.color.background_green))
                binding.llNoteHeaderBackground.setBackgroundColor(getColor(R.color.background_green))
                updateColorDialog.dismiss()
            }
            updateColorDialog.show()

        }

    }

    private fun getFormatedDate(date: Date): String {
        val formatter = SimpleDateFormat("MM-dd HH:mm", Locale.ENGLISH)
        return formatter.format(date)
    }


    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time

    }


}