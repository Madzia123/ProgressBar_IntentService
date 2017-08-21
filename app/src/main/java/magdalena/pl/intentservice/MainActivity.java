package magdalena.pl.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_EVENT_NAME = "broadcast-event-name";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textProgress)
    TextView textProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        int time = 50;
        progressBar.setMax(time);
        progressBar.setProgress(time);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(BROADCAST_EVENT_NAME));

        Intent intent = new Intent(this, ProgressBarService.class);
        intent.putExtra("interval", time);
        startService(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(ProgressBarService.KEY_EXTRA_PROGRESS)) {
                progressBar.setProgress(intent.getIntExtra(ProgressBarService.KEY_EXTRA_PROGRESS, 0));
                textProgress.setText("Nowe odlicznie odbedzie się za :" + intent.getIntExtra(ProgressBarService.KEY_EXTRA_PROGRESS, 0));
            }
        }
    };

}
