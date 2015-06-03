package com.fujin.splashanimation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {



    private Button end;

    private SplashingView round;

    private boolean isStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        round= (SplashingView) findViewById(R.id.round);
        end= (Button) findViewById(R.id.end);


       round.setOnFinishListener(new SplashingView.OnFinishListener() {
           @Override
           public void onFinish() {
//               Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//               startActivity(intent);
               round.setVisibility(View.GONE);
               end.setVisibility(View.GONE);

           }
       });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    round.finish();
                }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
