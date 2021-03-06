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
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.DetailsEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.PersonEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;

public class StartWorkout extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    VajaAdapter adapter;
    Workout clickedWorkout;
    TextView imeWorkoutaText;
    private Button finishBtn;
    private Button goBack;
//    private Button kljukica;
    Context context;
    AppDatabase db;




    // onClick za finish button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishBtn:
//                int cas = db.personDAO().getAll().get(0).timeDone + clickedWorkout.getTrajanje();
//                PersonEntity person = db.personDAO().getAll().get(0);
//                person.timeDone = cas;
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case R.id.backBtn:
                //gremo samo en layer back
                super.onBackPressed();
                break;
//            case R.id.recyclerViewStart:
//                //shranimo v bazo details, ki se nanaša na posamezni workout in posamezno vajo
//                int idWorkouta = clickedWorkout.getIdWorkouta();
//
//                DetailsEntity details = new DetailsEntity();
//                details.pripadaVaji = 1;
//                details.pripadaWorkoutu = idWorkouta;
//                details.reps = Integer.parseInt(((EditText)findViewById(R.id.stPonovitev)).getText().toString());
//                details.weight = Integer.parseInt(((EditText)findViewById(R.id.tezaUtezi)).getText().toString());
//                details.setNo = Integer.parseInt(((EditText)findViewById(R.id.stSetov)).getText().toString());
//                System.out.println("noviDetails "+ details);
//                break;
//            case R.id.kljukica:
//                System.out.println("idGumba"+kljukica.getId());

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickedWorkout = getIntent().getParcelableExtra("clickedWorkout");
//        System.out.println("dkjakfljd" + clickedWorkout.getIdWorkouta());
        setContentView(R.layout.fragment_start_workout);

        context  = this;

//        FINISH BUTTON
        finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);

//        GO BACK BUTTON
        goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(this);

//        KLJUKICA BUTTON
//        kljukica = findViewById(R.id.kljukica);
//        kljukica.setOnClickListener(this);

//        SET TEXT ZA IZBRAN WORKOUT
        imeWorkoutaText = (TextView)findViewById(R.id.izbranWorkout);
        imeWorkoutaText.setText(clickedWorkout.getIme());

        // ADAPTER PART1. get a reference to recyclerView
        recyclerView = findViewById(R.id.recyclerViewStart);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        System.out.println("vaje clicked workotua: " + clickedWorkout.getVaje());

        // 3. create an adapter
        System.out.println("predn gre v adapter " + clickedWorkout.getIdWorkouta());
        adapter = new VajaAdapter(clickedWorkout.getVaje(),null, clickedWorkout,2);

        // 4. set adapter
        recyclerView.setAdapter(adapter);
    }
}