package capstone.schwendimankw.campingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Kyle Schwendiman on 4/10/2018.
 */

public class PlanningActivity extends AppCompatActivity {

    private Button mCheckListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_activity);

        mCheckListButton = (Button)findViewById(R.id.checklist_button);
        mCheckListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlanningActivity.this, PlanningListActivity.class);
                startActivity(i);
            }
        });
    }
}
