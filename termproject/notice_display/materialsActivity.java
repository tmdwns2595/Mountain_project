package org.jjcouple.termproject.notice_display;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import org.jjcouple.termproject.R;

public class materialsActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materials_activity);

        VideoView view = (VideoView)findViewById(R.id.videoView1);
        String videopath ="android.resource://" + getPackageName()+ "/" + R.raw.materialsvideo;
        view.setVideoURI(Uri.parse(videopath));

        MediaController mediaController = new MediaController(this);
        view.setMediaController(mediaController);
        mediaController.setAnchorView(view);
    }
}
