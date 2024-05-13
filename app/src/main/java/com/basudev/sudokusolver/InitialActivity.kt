package com.basudev.sudokusolver

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InitialActivity : AppCompatActivity() {
    private var Difficulty = 0
    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var beginnerButton: Button
    private lateinit var playButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        //initialization of UI elements
        beginnerButton = findViewById<Button>(R.id.beginnerButton)
        easyButton = findViewById<Button>(R.id.easyButton)
        mediumButton = findViewById<Button>(R.id.mediumButton)
        playButton = findViewById(R.id.PlayButton)

        playButton.setBackgroundColor(Color.WHITE)
        playButton.setTextColor(Color.rgb(0,63,136))

    }
    fun clickBegginerButton(view: View?){
        mediumButton.setBackgroundColor(Color.rgb(0,63,136))
        mediumButton.setTextColor(Color.WHITE)
        easyButton.setBackgroundColor(Color.rgb(0,63,136))
        easyButton.setTextColor(Color.WHITE)

        Difficulty = 1
        beginnerButton.setBackgroundColor(Color.WHITE)
        beginnerButton.setTextColor(Color.rgb(0,63,136))
        playButton.setBackgroundColor(Color.rgb(0,63,136))
        playButton.setTextColor(Color.WHITE)
    }

    fun clickEasyButton(view: View?){
        mediumButton.setBackgroundColor(Color.rgb(0,63,136))
        mediumButton.setTextColor(Color.WHITE)
        beginnerButton.setBackgroundColor(Color.rgb(0,63,136))
        beginnerButton.setTextColor(Color.WHITE)

        Difficulty = 2
        easyButton.setBackgroundColor(Color.WHITE)
        easyButton.setTextColor(Color.rgb(0,63,136))
        playButton.setBackgroundColor(Color.rgb(0,63,136))
        playButton.setTextColor(Color.WHITE)
    }

    fun clickMediumButton(view: View?){
        beginnerButton.setBackgroundColor(Color.rgb(0,63,136))
        beginnerButton.setTextColor(Color.WHITE)
        easyButton.setBackgroundColor(Color.rgb(0,63,136))
        easyButton.setTextColor(Color.WHITE)

        Difficulty = 3
        mediumButton.setBackgroundColor(Color.WHITE)
        mediumButton.setTextColor(Color.rgb(0,63,136))
        playButton.setBackgroundColor(Color.rgb(0,63,136))
        playButton.setTextColor(Color.WHITE)
    }

    fun clickPlay(view: View?){
        if(Difficulty != 0){
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("DifficultyLevel", Difficulty)
            startActivity(intent)
        }
    }

    fun getDifficulty():Int{
        return Difficulty
    }
}