package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var player1: TextView
    private lateinit var player2: TextView
    private lateinit var scorex: TextView
    private lateinit var score0: TextView
    private lateinit var leftbtn: AppCompatButton

    private lateinit var btn1 : AppCompatButton
    private lateinit var btn2 : AppCompatButton
    private lateinit var btn3 : AppCompatButton
    private lateinit var btn4 : AppCompatButton
    private lateinit var btn5 : AppCompatButton
    private lateinit var btn6 : AppCompatButton
    private lateinit var btn7 : AppCompatButton
    private lateinit var btn8 : AppCompatButton
    private lateinit var btn9 : AppCompatButton

    private var firstPlayerScore = 0
    private var secondPlayerScore = 0

    var firstplayer = 0
    var secondplayer = 1
    var activeplayer = firstplayer
    lateinit var filledPos : IntArray

    var gameactive = true

    lateinit var rigi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // თამაშიდან გამოსვლა

        leftbtn = findViewById<AppCompatButton>(R.id.leftbtn)
        leftbtn.setOnClickListener {
            val intent = Intent(this@GameActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // EditText- ის მიღება LoginActivity-დან

        player1 = findViewById(R.id.player1)
        player2 = findViewById(R.id.player2)

        val firstplayer = intent.getStringExtra("first player")
        val secondplayer = intent.getStringExtra("second player")

        player1.text = firstplayer
        player2.text = secondplayer

        initial()

    }

    private fun initial() {

        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)

        rigi = findViewById<TextView>(R.id.rigi)

        scorex = findViewById(R.id.scorex)
        score0 = findViewById(R.id.score0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)


        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)

    }

    @SuppressLint("ResourceType")
    override fun onClick(v: View?) {

        if (!gameactive)
            return

        var btnclicked = findViewById<AppCompatButton>(v!!.id)
        var clickedtag = Integer.parseInt(btnclicked.tag.toString())

        if (filledPos[clickedtag] != -1)
            return

        filledPos[clickedtag] = activeplayer

        if (activeplayer == firstplayer) {
            btnclicked.setText("X")
            btnclicked.setTextColor(Color.BLACK)
            rigi.setText("" + player2.text + " is playing")
            btnclicked.backgroundTintList = getColorStateList(R.color.RED)
            activeplayer = secondplayer

        }else {
            btnclicked.setText("0")
            btnclicked.setTextColor(Color.BLACK)
            rigi.setText("" + player1.text + " is playing")
            btnclicked.backgroundTintList = getColorStateList(R.color.BLUE)
            activeplayer = firstplayer
        }
        CheckForWin()

    }

    private fun CheckForWin() {

        // მოგების პოზიციები

        var winPos = arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),

            intArrayOf(0,3,6),intArrayOf(1,4,7),intArrayOf(2,5,8),intArrayOf(0,4,8),intArrayOf(2,4,6)
        )

        for (i in 0 until winPos.size){
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {

                if (filledPos[val0]!=-1){
                    gameactive = false
                    if (filledPos[val0] == firstplayer){

                        showMessage("Winner is " + player1.text)

                        // ქულა მოემატოს X-ს

                        firstPlayerScore ++
                        scorex.text = firstPlayerScore.toString()

                    }else{

                        showMessage("Winner is " + player2.text)

                        // ქულა მოემატოს 0-სს

                        secondPlayerScore ++
                        score0.text = secondPlayerScore.toString()
                    }
                    return
                }
            }

        }

        //ფრე

        var count=0
        for (i in 0 until filledPos.size){
            if (filledPos[i]==-1){
                count++
            }
        }
        if (count==0){
            showMessage("It is draw")
            return
        }

    }
    private fun showMessage(s: String) {

        // შეტყობინების ფუნქცია

        AlertDialog.Builder(this)

            .setMessage(s)
            .setTitle("GAME RESULT")
            .setPositiveButton("PLAY AGAIN", DialogInterface.OnClickListener { dialog, which ->
                restartgame()
            })
            .show()

    }

    private fun restartgame() {

        // თამაშის დარესტარტება

        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activeplayer = firstplayer
        gameactive = true
        rigi.setText("PLEASE START GAME")
        rigi.setTextColor(Color.WHITE)
        btn1.setText("")
        btn1.backgroundTintList = getColorStateList(R.color.BLACK)
        btn2.setText("")
        btn2.backgroundTintList = getColorStateList(R.color.BLACK)
        btn3.setText("")
        btn3.backgroundTintList = getColorStateList(R.color.BLACK)
        btn4.setText("")
        btn4.backgroundTintList = getColorStateList(R.color.BLACK)
        btn5.setText("")
        btn5.backgroundTintList = getColorStateList(R.color.BLACK)
        btn6.setText("")
        btn6.backgroundTintList = getColorStateList(R.color.BLACK)
        btn7.setText("")
        btn7.backgroundTintList = getColorStateList(R.color.BLACK)
        btn8.setText("")
        btn8.backgroundTintList = getColorStateList(R.color.BLACK)
        btn9.setText("")
        btn9.backgroundTintList = getColorStateList(R.color.BLACK)
    }

}