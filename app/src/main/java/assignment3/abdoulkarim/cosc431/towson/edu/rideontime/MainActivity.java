package assignment3.abdoulkarim.cosc431.towson.edu.rideontime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(){

            @Override
            public void run() {
                try{
                    sleep(7000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent TowelcomeActivity = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(TowelcomeActivity);
                }
            }


        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
