package org.rafalesoft.com.jeuquentin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.rafalesoft.com.raptor.Raptor;

import java.io.IOException;


public class MainActivity extends AppCompatActivity
{
    private final GLRenderer mRenderer = new GLRenderer();
    private MediaPlayer mMediaPlayer = null;


    @Override
    protected void onStop()
    {
        super.onStop();
        mMediaPlayer.stop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMediaPlayer.release();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMediaPlayer.start();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        try
        {
            mMediaPlayer.prepare();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View glView = Raptor.glCreateWindow(this, mRenderer);
        ConstraintLayout containerView = findViewById(R.id.gl_container);
        containerView.addView(glView);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.track4);
        mMediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_message);
        builder.setPositiveButton(R.string.exit_yes, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                mMediaPlayer.stop();
                finish();
            }
        });
        builder.setNegativeButton(R.string.exit_no, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
