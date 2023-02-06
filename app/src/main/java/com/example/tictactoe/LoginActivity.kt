package com.example.tictactoe
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    lateinit var gamebutton : Button
    lateinit var firstplayer : EditText
    lateinit var secondplayer : EditText
    lateinit var backbutton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        gamebutton = findViewById(R.id.gamebtn)
        firstplayer = findViewById(R.id.firstplayer)
        secondplayer = findViewById(R.id.secondplayer)

        //გასვლა LoginActivity-დან MainActivity-ზე

        backbutton = findViewById(R.id.backbtn)
        backbutton.setOnClickListener {
            val intent = Intent(this , WelcomeActivity::class.java)
            startActivity(intent)
        }

        // თამაშში შესვლა LoginActiivty- დან GameActivity-ზე

        gamebutton.setOnClickListener {

            //თუ სახელებს არ შევიყვანთ თამაშს ვერ დავიწყებთ

            val playerone = firstplayer.text.toString()
            val playertwo = secondplayer.text.toString()

            if (playerone.isEmpty() or playertwo.isEmpty()) {
                Toast.makeText(this@LoginActivity, "PLEASE INPUT NAMES!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else {

                /* თუ სახელებს შევიყვანთ გადავალთ და დავაწვებით gamebutton-ს დავიწყებთ თამაშს და
                ასევე ჩვენს მიერ შეყვანილ სახელებს გადაიტანს GameActivity-ზე putExtra-ს დახმარებით */

                startActivity(Intent(this, GameActivity::class.java)
                    .putExtra("first player", firstplayer.text.toString())
                    .putExtra("second player", secondplayer.text.toString()))
            }
        }

    }

}