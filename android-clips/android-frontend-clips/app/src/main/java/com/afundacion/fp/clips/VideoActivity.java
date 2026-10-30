package com.afundacion.fp.clips;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    public static final String INTENT_CLIP_ID = "CLIP_ID";
    public static final String INTENT_CLIP_URL = "CLIP_URL";

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.video_view);

        Intent intent = getIntent();
        int clipId = intent.getIntExtra(VideoActivity.INTENT_CLIP_ID, -1);
        String clipUrl = intent.getStringExtra(VideoActivity.INTENT_CLIP_URL);
        Toast.makeText(this, "clipID: " + clipId, Toast.LENGTH_LONG).show();

        // Al terminar el vídeo, cerramos la actividad y volvemos a la lista
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
            }
        });

        videoView.setVideoURI(Uri.parse(clipUrl));
        videoView.start();
    }
}