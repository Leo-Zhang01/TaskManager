package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter aa;
    Button button;
    ArrayList<String> taskArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Databasehelper db = new Databasehelper(MainActivity.this);
        taskArrayList = db.getAllTask();
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,taskArrayList);
        listView.setAdapter(aa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),AddTask.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Databasehelper db = new Databasehelper(MainActivity.this);

        taskArrayList = db.getAllTask();
        aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,taskArrayList);

        listView.setAdapter(aa);
    }
}
