package com.tksoft.weather2018.ui.details;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.services.PlayMusicService;
import com.tksoft.weather2018.ui.base.BaseActivity;
import com.tksoft.weather2018.ui.details.fragment.FragmentAddfr;
import com.tksoft.weather2018.ui.details.fragment.NotifitionFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginSuccessActivity extends BaseActivity {
    @BindView(R.id.btn_news_feed)
    ImageButton btnNewsFeed;
    @BindView(R.id.btn_addfr)
    ImageButton btnAddfr;
    @BindView(R.id.btn_mess)
    ImageButton btnMess;
    @BindView(R.id.btn_notifition)
    ImageButton btnNotifition;
    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_user)
    ImageView btnUser;
    private NotificationManager mNotificationManager;


    private NotifitionFragment mFragment = new NotifitionFragment();
    private FragmentAddfr mFragmentFr = new FragmentAddfr();
    private Intent intent;
    private int REQUEST_CODE_CAMERA = 123;
    private int mId = 001;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onViewCreated() {
        LoginNotificationUtils.create(this);
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_notifition)
                .setContentTitle("My notification")
                .setContentText("This is notification from app Login");

        Intent intent = new Intent(this, DetailsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
         mBuilder.setContentIntent(pendingIntent);
         mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         mNotificationManager.notify(mId, mBuilder.build());

    }

    @Override
    public void setActionForViews() {

    }

    @OnClick(R.id.btn_notifition)
    public void onNotifition() {
        pushFragment(true, R.id.frame_fragment_login, mFragment);
        Toast.makeText(this, "Notifition", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_mess)
    public void onMess() {
        intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body", "Hello");
        intent.setData(Uri.parse("sms:0985231879"));
        startActivity(intent);
        Toast.makeText(this, "Mess", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_addfr)
    public void onAddfr() {
        pushFragment(true, R.id.frame_fragment_login, mFragmentFr);
        Toast.makeText(this, "Add friend", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_news_feed)
    public void onNewsFeed() {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
        clearBackStack();
        Toast.makeText(this, "News Feed", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_play)
    public void onPlayer(){
        Intent intent = new Intent(this, PlayMusicService.class);
        startService(intent);
    }

    @OnClick(R.id.btn_stop)
    public void onStopPlayer(){
        Intent intent = new Intent(this, PlayMusicService.class);
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        mNotificationManager.cancel(mId);
        super.onDestroy();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_login_fb, menu);
//        return true;
//    }
}
