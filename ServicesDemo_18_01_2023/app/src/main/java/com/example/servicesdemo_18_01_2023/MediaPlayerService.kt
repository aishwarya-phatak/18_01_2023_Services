package com.example.servicesdemo_18_01_2023

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class MediaPlayerService : Service() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var uri: Uri
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        Log.e("tag","onCreate Method os Service is called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("tag","onStartCommand")
        Toast.makeText(this,"onStartCommand",Toast.LENGTH_LONG).show()

        if(intent != null){
            var path = intent.getStringExtra("file_path")
            uri = Uri.parse(path)
            Toast.makeText(this,"$startId -- ${intent.getStringExtra("file_path")}",Toast.LENGTH_LONG).show()
        }
        //   /storage/emulated/0/Music/audio_file.mp3
        mediaPlayer = MediaPlayer.create(this,uri)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this,"onBindCalled",Toast.LENGTH_LONG).show()
        Log.e("tag","onBind Called")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"onDestroyCalled",Toast.LENGTH_LONG).show()
        Log.e("tag","onDestroyCalled")
        mediaPlayer.stop()

    }
}