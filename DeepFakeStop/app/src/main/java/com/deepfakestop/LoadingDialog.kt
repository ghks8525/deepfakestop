package com.deepfakestop

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class LoadingDialog: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_loading, container)

        val window = dialog!!.window
        window!!.setBackgroundDrawableResource(R.color.transparent)

        val windowParams = window.attributes

        windowParams.dimAmount = 0.50f
        windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        window.attributes = windowParams

        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        init(view)

        return view
    }

    fun init(v:View){
        var ain = v.findViewById<ImageView>(R.id.loading_img)
        Log.d("jjj", "aaaaaaaaaaaaaaaaa = ${ain?.id}")

        val animationDrawable: AnimationDrawable = ain?.drawable as AnimationDrawable

        animationDrawable.start()
    }

}