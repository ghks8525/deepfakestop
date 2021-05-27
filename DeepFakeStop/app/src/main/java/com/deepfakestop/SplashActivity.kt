package com.deepfakestop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.deepfakestop.databinding.ActivitySplashBinding

class SplashActivity:AppCompatActivity() {
    private lateinit var mbinding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        },3000)
    }
}