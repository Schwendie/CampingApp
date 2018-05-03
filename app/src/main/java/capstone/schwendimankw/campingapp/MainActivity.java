package capstone.schwendimankw.campingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mPlanningButton;
    private Button mCampingButton;
    private Button mSurvivingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlanningButton = (Button)findViewById(R.id.planning_button);
        mPlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlanningListActivity.class);
                startActivity(i);
            }
        });

        mCampingButton = (Button)findViewById(R.id.camping_button);
        mCampingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CampingListActivity.class);
                startActivity(i);
            }
        });

        mSurvivingButton = (Button)findViewById(R.id.surviving_button);
        mSurvivingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SurvivingListActivity.class);
                startActivity(i);
            }
        });
    }
}
