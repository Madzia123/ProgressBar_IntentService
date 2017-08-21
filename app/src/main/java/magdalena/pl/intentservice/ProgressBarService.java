package magdalena.pl.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;


public class ProgressBarService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    private int interval;
    private static final String TAG = ProgressBarService.class.getSimpleName();
    public static final String KEY_EXTRA_PROGRESS = "progress";



    public ProgressBarService() {
        super(ProgressBarService.class.getSimpleName());
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            interval = intent.getIntExtra("interval",0);
            Intent broadcastIntent = new Intent(MainActivity.BROADCAST_EVENT_NAME);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
            for (int i = interval; i >= 0 ; i--) {
                broadcastIntent = new Intent(MainActivity.BROADCAST_EVENT_NAME);
                broadcastIntent.putExtra(KEY_EXTRA_PROGRESS, i);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
                SystemClock.sleep(1000);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
