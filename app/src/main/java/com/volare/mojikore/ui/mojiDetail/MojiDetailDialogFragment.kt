package com.volare.mojikore.ui.mojiDetail

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.volare.mojikore.R
import com.volare.mojikore.binding.RecyclerViewBinding
import kotlinx.android.synthetic.main.moji_detail_dialog.view.*
import kotlinx.android.synthetic.main.moji_grid_view.view.*

class MojiDetailDialogFragment : DialogFragment() {
    private val args: MojiDetailDialogFragmentArgs by navArgs()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.v("DialogFragment", "${args.url}")
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater;
        val view = inflater.inflate(R.layout.moji_detail_dialog, null)

        RecyclerViewBinding.bindImage(view.moji_detail_image, args.url)

        val closeBtn = view.findViewById<ImageButton>(R.id.ic_close)

        builder.setView(view)
        val a = builder.create()
        a.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        closeBtn.setOnClickListener { view: View ->
            Log.v("DialogFragment", "called close button")
            a.dismiss()
        }
        return a
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}