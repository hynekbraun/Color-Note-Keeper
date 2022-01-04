package com.hynekbraun.notekeeper2.UI

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
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
    private val currentDate = getCurrentDateTime()
    private lateinit var _binding: ActivityNoteBinding
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.tvNoteDate.text = getFormatedDate(currentDate)

        setUpButtons()
    }

    private fun setUpButtons() {
        binding.ivNoteSave.setOnClickListener {
            val header = binding.etNoteHeader.text.toString()
            val description = binding.etNoteDescription.text.toString()
            val color = (binding.tvNoteColorChange.background as ColorDrawable).color
            val isDone = binding.cbNoteIsDone.isChecked
            if (header.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in the header and note", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addNote(NoteEntity(0, header, description, currentDate, color, isDone))
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                goToMainActivity()
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

    private fun showDialog() {
        lateinit var dialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you want to save current note?")
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                    viewModel.addNote(
                        NoteEntity(
                            0,
                            binding.etNoteHeader.text.toString(),
                            binding.etNoteDescription.text.toString(),
                            getCurrentDateTime(),
                            (binding.tvNoteColorChange.background as ColorDrawable).color,
                            false
                        )
                    )
                    goToMainActivity()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    goToMainActivity()
                }
            }
        }
        builder.setPositiveButton("YES", dialogClickListener)

        builder.setNegativeButton("NO", dialogClickListener)

        builder.setNeutralButton("CANCEL", dialogClickListener)

        dialog = builder.create()
        dialog.show()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (binding.etNoteHeader.text.isNotEmpty() && binding.etNoteDescription.text.isNotEmpty()) {
            showDialog()
        } else {
            super.onBackPressed()
        }
    }
}