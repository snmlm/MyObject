package demosvn.imagedownload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            image.setImageBitmap((Bitmap) msg.obj);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image_image);




    }

    public void onClick(View v){
        Glide.with(this).load("http://192.168.1.124:8079/tomcat.png").into(image);
        /*new Thread(){
            @Override
            public void run() {
                try {
                    String a = "http://192.168.1.124:8079/tomcat.png";
                    URL url = new URL(a);
                    HttpURLConnection content = (HttpURLConnection) url.openConnection();
                    content.setRequestMethod("GET");
                    content.setConnectTimeout(5000);
                    content.setReadTimeout(5000);
                    content.connect();
                    if(content.getResponseCode() == 200){
                        Message msg = handler.obtainMessage();
                        msg.obj = BitmapFactory.decodeStream(content.getInputStream());
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}
