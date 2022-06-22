package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.DetailsEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;

public class EmptyWorkout extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    ArrayList<Vaja> listVsehVaj;
    ArrayList<Vaja> listVajZaNewWrk;

    Workout novWorkout;
    EditText novaTelovadbaIme;
    private Button finishBtn;
    private Button goBack;
    private Button dodajBtn;
    Context context;
    VajaAdapter adapter;


    // onClick za finish button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishBtn:
                //insertamo se ime workouta
                novaTelovadbaIme = (EditText)findViewById(R.id.novaTelovadbaIme);
                new Thread(){

                    @Override
                    public void run() {
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

                        List<WorkoutEntity> w1 = db.workoutDAO().getAll();
                        for (int i = 0; i < w1.size(); i++) {
                            System.out.println("++ ime wokrouta: "+w1.get(i).imeWorkouta );
                        }
                        System.out.println();

                        System.out.println("novaTelovadbaIme.toString()" + novaTelovadbaIme.getText().toString());

                        db.workoutDAO().updateImeWorkouta(novaTelovadbaIme.getText().toString(), novWorkout.getIdWorkouta());

                        List<WorkoutEntity> w2 = db.workoutDAO().getAll();
                        for (int i = 0; i < w2.size(); i++) {
                            System.out.println("++ ime wokrouta: "+w2.get(i).imeWorkouta );
                        }
                        //vrne podatke v main thread
                    }
                }.start();

                //gremo na glavno aktivnost in na fragment workout ki se itak po defaultu prizge
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case R.id.backBtn:
                //gremo samo en layer back
                super.onBackPressed();
                break;
            case R.id.dodajBtn:

                LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup, null);

//                TextView textNadWorkouti = (TextView) popupView.findViewById(R.id.textNadWorkouti);
//                textNadWorkouti.setText("Add " + listVaj.get(position).getImeVaje() + " to workout: ");

                // lets taps outside the popup also dismiss it
                boolean focusable = true;
                Drawable background = ContextCompat.getDrawable(context, R.drawable.popup_card);
                final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, focusable);

                //cancel popup on click of "x"
                MaterialButton xButton = (MaterialButton) popupView.findViewById(R.id.cancelPopup);
                xButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setBackgroundDrawable(background);
                popupWindow.setElevation(60);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                //klicemo recyclerview za popup
                RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.recyclerView);

                //setamo layout manager
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(mLayoutManager);

                //klicemo adapter in mu podamo listWorkoutov
                EmptyWorkoutPopupAdapter adapter = new EmptyWorkoutPopupAdapter(context, popupWindow, novWorkout, listVsehVaj);

                //setamo adapter
                recyclerView.setAdapter(adapter);


                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        new Thread(){

                            @Override
                            public void run() {
                                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

                                List<WorkoutVaje> vw = db.workoutVajeDAO().getVajeByWID(novWorkout.getIdWorkouta());

                                //vrne podatke v main thread
                                runOnUiThread( () -> setVajaAdapter(vw));
                            }
                        }.start();

                    }
                });

                break;
            default:
                break;
        }

    }

    public void setVajaAdapter(List<WorkoutVaje> vw){
        listVajZaNewWrk.clear();

            for (int j = 0; j < vw.get(0).vajaEntityList.size(); j++) {
                VajaEntity v = vw.get(0).vajaEntityList.get(j);
                System.out.println("printinggggggggggggg");
                System.out.println("listVajZaNewWrk: " + v.imeVaje);
                listVajZaNewWrk.add(new Vaja(v.idVaje, v.imeVaje, v.muscleG, v.imgVaje, v.desc, v.cals, v.recomendedReps, v.recommendedSets));

            }

            adapter.notifyDataSetChanged();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listVsehVaj = new ArrayList<Vaja>();
        listVajZaNewWrk = new ArrayList<>();

        setContentView(R.layout.activity_empty_workout);
        context  = this;

//        FINISH BUTTON
        finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);

//        GO BACK BUTTON
        goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(this);

        // DODAJ BUTTON
        dodajBtn = findViewById(R.id.dodajBtn);
        dodajBtn.setOnClickListener(this);

        //INSERT NOV WORKOUT V BAZO
        new Thread(){

            @Override
            public void run() {
                //tole je sam link do baze and as far as i know u can call it multiple times
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();

                WorkoutEntity nov = new WorkoutEntity();
                nov.imeWorkouta = "Nova telovadba";
                nov.isCustom = 1;
                nov.trajanje = 0;
                nov.totalCals = 0;
                nov.pripadaOsebi = db.personDAO().getAll().get(0).idPerson;
                int idInsertanega = (int) db.workoutDAO().insert(nov);


                List<VajaEntity> ve = db.vajeDao().getAll();
                for (int i = 0; i < ve.size(); i++) {
                    listVsehVaj.add(new Vaja(ve.get(i).idVaje, ve.get(i).imeVaje,ve.get(i).muscleG, R.drawable.dumbbell_icon, ve.get(i).desc, ve.get(i).cals, ve.get(i).recomendedReps, ve.get(i).recommendedSets));
                }

                novWorkout = new Workout(idInsertanega,"Dodaj novo ime telovadbe", 0,0,listVajZaNewWrk );
                //vrne podatke v main thread
//                getActivity().runOnUiThread( () -> setWorkoutAdapter(w, wv));
            }
        }.start();


//        SET TEXT ZA IZBRAN WORKOUT
//        imeWorkoutaText = (TextView)findViewById(R.id.izbranWorkout);
//        imeWorkoutaText.setText("");

        // ADAPTER PART1. get a reference to recyclerView
        recyclerView = findViewById(R.id.recyclerViewStart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new VajaAdapter(listVajZaNewWrk,null,null, 0);

        recyclerView.setAdapter(adapter);


//        dialog.setOnCancelListener(new OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                //do somethiing
//            }
//        });
    }

}