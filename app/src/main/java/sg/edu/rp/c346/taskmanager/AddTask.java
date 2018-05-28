package sg.edu.rp.c346.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {
    Button addBtn, cancelBtn;
    EditText name, desc, sec;
    int requestCode=12345,notificationID=888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addBtn = findViewById(R.id.add);
        cancelBtn = findViewById(R.id.cancel);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        sec = findViewById(R.id.sec);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameS = name.getText().toString();
                String descS = desc.getText().toString();
                int secS = Integer.parseInt(sec.getText().toString());

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, secS);

                Databasehelper db = new Databasehelper(AddTask.this);
                db.insertNote(nameS,descS);


                Intent intent = new Intent(getBaseContext(),
                        MyReceiver.class);
                Task task = new Task(nameS,descS);
                intent.putExtra("name",nameS);
                intent.putExtra("desc",descS);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddTask.this, requestCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
                finish();

            }
        });
    }
}
