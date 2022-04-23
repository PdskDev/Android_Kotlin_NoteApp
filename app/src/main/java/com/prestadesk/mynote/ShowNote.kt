package com.prestadesk.mynote

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.prestadesk.mynote.databinding.ShowNoteBinding

//Class to display Note (DialogFragment) + injection objet note + index note
class ShowNote(private val note: Note, private val index: Int): DialogFragment()
{
    //Properties
    private var _binding: ShowNoteBinding? = null
    private val binding get() = _binding!!

    //OnCreateDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val callingActivity = activity as MainActivity
        val inflater = callingActivity.layoutInflater

        //Inflater binding
        _binding = ShowNoteBinding.inflate(inflater)

        //Builder AlertDialog
        val builder = AlertDialog.Builder(callingActivity)
            .setView(binding.root)

        //Binding des widgets
        binding.txtTitle.text = note.title
        binding.txtContents.text = note.contents

        //Bouton OK de ShowNote
        binding.btnOK.setOnClickListener {
            dismiss()
        }
        //Bouton Delete de ShowNote
        binding.btnDelete.setOnClickListener {
            callingActivity.deleteNote(index)
            Toast.makeText(callingActivity, resources.getString(R.string.note_deleted), Toast.LENGTH_SHORT).show()
            dismiss()
        }
        //Create builder
        return builder.create()
    }

    //OnDestroyView
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}