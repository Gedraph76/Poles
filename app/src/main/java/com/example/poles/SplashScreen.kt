package com.example.poles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen : AppCompatActivity() {

    internal val Time = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash_screen)

        val top_bottom_animation = AnimationUtils.loadAnimation(this,R.anim.top_bottom_animation)
        val bottom_top_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_top_animation)
        val bounce_animation = AnimationUtils.loadAnimation(this,R.anim.bounce_animation)

        img_top_bottom.startAnimation(top_bottom_animation)
        tv_bounce.startAnimation(bounce_animation)
        img_bottom_top.startAnimation(bottom_top_animation)

        Handler().postDelayed(
            {
                startActivity(Intent(this,patients::class.java))
                finish()
            },Time.toLong()
        )
    }
}
