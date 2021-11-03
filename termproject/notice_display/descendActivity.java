package org.jjcouple.termproject.notice_display;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.jjcouple.termproject.R;

public class descendActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descend_activity);

        VideoView view = (VideoView)findViewById(R.id.videoView4);
        String videopath ="android.resource://" + getPackageName()+ "/" + R.raw.descendnoticevideo;
        view.setVideoURI(Uri.parse(videopath));

        MediaController mediaController = new MediaController(this);
        view.setMediaController(mediaController);
        mediaController.setAnchorView(view);
    }
}
