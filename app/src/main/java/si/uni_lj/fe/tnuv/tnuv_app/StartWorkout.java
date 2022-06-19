package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartWorkout extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    VajaAdapter adapter;
    Workout clickedWorkout;
    TextView myAwesomeTextView;
    private Button finishBtn;
    Context context;

    // onClick za finish button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishBtn:
                super.onBackPressed();
                System.out.println("Finish button pressed!");
                break;
//            case R.id.startButn:
//                Intent intent = new Intent(context, StartWorkout.class);
//                intent.putExtra("clickedWorkout", clickedWorkout);
//                context.startActivity(intent);
//                break;
            default:
                System.out.println(" ratattata Finish button pressed!");
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickedWorkout = getIntent().getParcelableExtra("clickedWorkout");
        //clickedWorkout = getIntent().getParcelableExtra("mylist");

        setContentView(R.layout.fragment_start_workout);
        System.out.println("clickedWorkoutstart workout"+clickedWorkout);

        context  = this;

        //FINISH BUTTON
//        finishBtn = findViewById(R.id.backBtn);
//        finishBtn.setOnClickListener(this);

        //SET TEXT ZA IZBRAN WORKOUT
        myAwesomeTextView = (TextView)findViewById(R.id.izbranWorkout);
        myAwesomeTextView.setText(clickedWorkout.getIme());

        // ADAPTER PART1. get a reference to recyclerView
        recyclerView = findViewById(R.id.recyclerViewStart);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. create an adapter
        adapter = new VajaAdapter(clickedWorkout.getVaje(), false);

        // 4. set adapter
        recyclerView.setAdapter(adapter);
    }
}