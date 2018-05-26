package co.edureka.edurekamay12session;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyMusicActivity extends AppCompatActivity {

    String songToPlay;
    MediaPlayer mediaPlayer;

    Button btn;
    TextView txt;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);

        songToPlay = Environment.getExternalStorageDirectory().getPath()+"/Nights.mp3";

        mediaPlayer = new MediaPlayer();

        // Online Streaming
        //songToPlay = "https://somedomain.com/music/nights.mp3";

        btn = findViewById(R.id.buttonPlay);
        txt = findViewById(R.id.textView6);
        imageView = findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);

        btn.startAnimation(animation);
        txt.startAnimation(animation1);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public void playMusic(View view){

        try{

            mediaPlayer.setDataSource(songToPlay);

            //mediaPlayer.setDataSource(this, Uri.parse(songToPlay));

            mediaPlayer.prepare();
            mediaPlayer.start();

            //mediaPlayer.pause();
            //mediaPlayer.seekTo(1200);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void stopMusic(View view){

        mediaPlayer.stop();
        mediaPlayer.release();

    }
}
