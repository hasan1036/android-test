package com.example.ecg_monitoring_system1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity2 extends AppCompatActivity {

    Button btn4;

    EditText xValue, yValue;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        btn4 = findViewById(R.id.btn4);
        xValue = findViewById(R.id.x_valueid);
        yValue = findViewById(R.id.y_valueid);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference chartTable = database.getReference("ChartValues");
        DatabaseReference ledDbt2 = database.getReference("ledDbt2");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count += 5;
                                xValue.setText(String.valueOf(count));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        ledDbt2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String value = snapshot.getValue().toString();
                yValue.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        autoClick();

        // Button Start
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Button End
    }

    private void autoClick() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference chartTable = database.getReference("ChartValues");

        Thread t = new Thread(){
            @Override
            public  void run()
            {while (!isInterrupted()){
                try {Thread.sleep(1590);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String id = chartTable.push().getKey();
                            int x = Integer.parseInt(xValue.getText().toString());
                            int y = Integer.parseInt(yValue.getText().toString());//y
                           // int  valuenum = y / 7;
//
//
//                            //we calculate pqrst
//                            int p = (int) (valuenum * 1.1);
//                            int q = (int) (valuenum * 0.8);
//                            int r =(int) (valuenum *  2.0);
//                            int s = (int) (valuenum *  0.7);
//                            int t = (int) (valuenum * 1.0);
                          //  int p = Integer.parseInt(valuenum.toString());


                            // DataPoint pointValue = new DataPoint(x,valuenum);
                            DataPoint pointValue = new DataPoint(x,y);
                            chartTable.child(id).setValue(pointValue);}});
                }catch (InterruptedException e){e.printStackTrace();}}}
        };
        t.start();
    }
}