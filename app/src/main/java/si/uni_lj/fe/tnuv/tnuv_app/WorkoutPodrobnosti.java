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

public class WorkoutPodrobnosti extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    VajaAdapter adapter;
    Workout clickedWorkout;
    TextView myAwesomeTextView;
    private Button backBtn;
    private Button startBtn;
    Context context;


    // onClick za back button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                super.onBackPressed();
                //System.out.println("pritisnjen back button");
                break;
            case R.id.startButn:
                Intent intent = new Intent(context, StartWorkout.class);
                intent.putExtra("clickedWorkout", clickedWorkout);
                context.startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_podrobnosti);
        clickedWorkout = getIntent().getParcelableExtra("mylist");

       context  = this;

        System.out.println("passedWorkout ime: " + clickedWorkout.getIme() + " vaje: " + clickedWorkout.getVaje());

        //BACK BUTTON
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        //START BUTTON
        startBtn = findViewById(R.id.startButn);
        startBtn.setOnClickListener(this);


        //SET TEXT ZA IZBRAN WORKOUT
        myAwesomeTextView = (TextView)findViewById(R.id.izbranWorkout);
        myAwesomeTextView.setText(clickedWorkout.getIme());

        // ADAPTER PART
        // 1. get a reference to recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. create an adapter
        adapter = new VajaAdapter(clickedWorkout.getVaje(), null,null,0);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

    }


}


//    public static void insertTask(final VajaEntity vaja) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                VajaEntity.insertTask(vaja);
//                return null;
//            }
//        }.execute();
//    }

//        getApplicationContext().deleteDatabase("database-name");
//thread implements runnable ki ima metodo run fuck me life
//        new Thread(){
//
//            //anonimni notranji razred
//            @Override
//            public void run() {
//
//                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
//
//                List<VajaEntity> v = db.vajeDao().getAll();
//
//                for (int i = 0; i < v.size(); i++) {
//                    System.out.println("** "+v.get(i).imeVaje+", " +v.get(i).id);
//                }
//
//                //vrne podatke v main thread
////                runOnUiThread( () -> prikaziPodatke(rezultat));
//
//            }
//        }.start();


//        VajaEntity vaja = new VajaEntity();
//        vaja.imeVaje = "novo";
//        vaja.muscleG = "Legs";
//        vaja.imgVaje = 10;
//        db.vajeDao().insert(vaja);
//        List<VajaEntity> v = db.vajeDao().getAll();
//        for (int i = 0; i < v.size(); i++) {
//            System.out.println("** "+v.get(i).imeVaje+", " +v.get(i).id);
//        }
//
//        db.vajeDao().delete(v.get(0));
//        v = db.vajeDao().getAll();
//        for (int i = 0; i < v.size(); i++) {
//            System.out.println("**** "+v.get(i).imeVaje);
//        }
//        insertTask(vaja);