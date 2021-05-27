package com.deepfakestop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepfakestop.databinding.ActivityResultBinding

class ResultActivity:AppCompatActivity() {
    private lateinit var mBinding:ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_result)
        mBinding.listener = this
    }

    fun onClick(v: View){
        when(v.id){
            R.id.url -> {

            }
        }
    }
}