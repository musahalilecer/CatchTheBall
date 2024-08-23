package com.musahalilecer.catchtheball_2

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import com.musahalilecer.catchtheball_2.databinding.ActivityMainBinding
import java.util.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var imageArray = ArrayList<ImageView>()
    var score = 0
    var runnable = Runnable{ }
    var handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)




    }

    fun hideImage(){
        runnable = object : Runnable{
            override fun run() {
                for(images in imageArray){
                    images.visibility = View.INVISIBLE
                }
                var random = Random()
                var randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
    fun increaseScore(view: View){
        score = score + 1
        binding.scoreView.text = "Score: ${score}"
    }
    fun start(view: View){
        object : CountDownTimer(5000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeView.text = "Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.timeView.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for(images in imageArray){
                    images.visibility = View.VISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game")
                alert.setPositiveButton("Yes"){dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which ->
                    Toast.makeText(this@MainActivity,"Game was Finished",Toast.LENGTH_LONG)
                }
                alert.show()
            }
        }.start()
        hideImage()
    }
}