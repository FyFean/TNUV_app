package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StartWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Workout clickedWorkout = getIntent().getParcelableExtra("clickedWorkout");

        setContentView(R.layout.activity_start_workout);
        System.out.println("clickedWorkout"+clickedWorkout);
    }
}