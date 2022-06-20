package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StartWorkout extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    VajaAdapter adapter;
    Workout clickedWorkout;
    TextView imeWorkoutaText;
    private Button finishBtn;
    private Button goBack;
    Context context;




    // onClick za finish button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishBtn:
                //gremo na glavno aktivnost in na fragment workout ki se itak po defaultu prizge
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case R.id.backBtn:
                //gremo samo en layer back
                super.onBackPressed();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickedWorkout = getIntent().getParcelableExtra("clickedWorkout");
        setContentView(R.layout.fragment_start_workout);

        context  = this;

//        FINISH BUTTON
        finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);

//        GO BACK BUTTON
        goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(this);

//        SET TEXT ZA IZBRAN WORKOUT
        imeWorkoutaText = (TextView)findViewById(R.id.izbranWorkout);
        imeWorkoutaText.setText(clickedWorkout.getIme());

        // ADAPTER PART1. get a reference to recyclerView
        recyclerView = findViewById(R.id.recyclerViewStart);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. create an adapter
        adapter = new VajaAdapter(clickedWorkout.getVaje(),null, 2);

        // 4. set adapter
        recyclerView.setAdapter(adapter);
    }



}