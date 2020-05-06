package com.gmail.velikiydan.mobile.ui.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.gmail.velikiydan.mobile.R
import kotlinx.android.synthetic.main.activity_audio.*


class AudioActivity : AppCompatActivity() {
    lateinit var soundPlayer: MediaPlayer
    lateinit var soundThread: Thread
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        soundPlayer = MediaPlayer.create(this, R.raw.jojo);

        setupListeners();

        runnable = Runnable { this.run() }

        soundThread = Thread(runnable);
        soundThread.start();


    }

    private fun setupListeners() {
        startButton.setOnClickListener {
            soundPlayer.start()
        }
        pauseButton.setOnClickListener {
            soundPlayer.pause()
        }
        stopButton.setOnClickListener {
            soundPlayer.stop()
            soundPlayer = MediaPlayer.create(this, R.raw.jojo)
        }
        soundSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                if (fromUser) {
                    soundPlayer.seekTo(progress)
                }
            }
        })

    }

    private fun run() {
        var currentPosition = 0
        val soundTotal: Int = soundPlayer.getDuration()
        soundSeekBar.setMax(soundTotal)
        while (currentPosition < soundTotal) {
            currentPosition = try {
                Thread.sleep(300)
                soundPlayer.getCurrentPosition()
            } catch (soundException: InterruptedException) {
                return
            } catch (otherException: Exception) {
                return
            }
            soundSeekBar.setProgress(currentPosition)
        }
    }


}


