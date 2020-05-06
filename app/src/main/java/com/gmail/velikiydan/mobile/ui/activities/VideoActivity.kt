package com.gmail.velikiydan.mobile.ui.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.gmail.velikiydan.mobile.R
import kotlinx.android.synthetic.main.activity_video.*

var url =
    "https://ia801602.us.archive.org/11/items/Rick_Astley_Never_Gonna_Give_You_Up/Rick_Astley_Never_Gonna_Give_You_Up.mp4"

class VideoActivity : AppCompatActivity() {

    lateinit var videoController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        videoController = MediaController(this)
        setupMedia()
    }

    private fun setupMedia() {
        videoView.setMediaController(videoController)
        videoView.setVideoPath(url)
        videoView.start()
    }
}
