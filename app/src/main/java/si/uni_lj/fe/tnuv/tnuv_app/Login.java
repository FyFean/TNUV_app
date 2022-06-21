package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.CrossRefVajaWorkout;
import si.uni_lj.fe.tnuv.tnuv_app.database2.DetailsEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.PersonEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;

public class Login extends AppCompatActivity {

    private Button vpisBtn;
    Context context;
    EditText imePriimek;
    EditText teza;
    EditText visina;
    EditText zeljeneKalorije;
    EditText zeljenCas;
    Spinner spol;
    Spinner s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        vpisBtn = findViewById(R.id.vpisBtn);
        vpisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imePriimek = (EditText)findViewById(R.id.imePrii);
                teza = (EditText)findViewById(R.id.teza2);
                visina = (EditText)findViewById(R.id.visina2);
                zeljeneKalorije = (EditText)findViewById(R.id.calInput);
                zeljenCas = (EditText)findViewById(R.id.cas2);
                spol = (Spinner)findViewById(R.id.spinner);

                if("".equals(imePriimek.getText().toString())){
                    Toast.makeText(context,"Prosim izpolnite polje za ime.",Toast.LENGTH_LONG).show();
                }else if("".equals(teza.getText().toString())){
                    Toast.makeText(context,"Prosim izpolnite polje za težo.",Toast.LENGTH_LONG).show();
                }else if("".equals(visina.getText().toString())){
                    Toast.makeText(context,"Prosim izpolnite polje za višino.",Toast.LENGTH_LONG).show();
                }else if("".equals(zeljeneKalorije.getText().toString())){
                    Toast.makeText(context,"Prosim izpolnite polje za željene kalorije.",Toast.LENGTH_LONG).show();
                }else if("".equals(zeljenCas.getText().toString())){
                    Toast.makeText(context,"Prosim izpolnite polje za željen čas vadbe.",Toast.LENGTH_LONG).show();
                }else {
                    new Thread() {
                        @Override
                        public void run() {
                            getApplicationContext().deleteDatabase("database-name");
                            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
                            PersonEntity oseba1 = new PersonEntity();
                            oseba1.imePriimek = imePriimek.getText().toString();
                            oseba1.spol = spol.getSelectedItem().toString();
                            oseba1.caloriesGoal = Integer.parseInt(zeljeneKalorije.getText().toString());
                            oseba1.timeGoal = Integer.parseInt(zeljenCas.getText().toString());
                            oseba1.bodyWeight = Integer.parseInt(teza.getText().toString());
                            oseba1.bodyHeight = Integer.parseInt(visina.getText().toString());
                            oseba1.timeDone = 0;
                            oseba1.caloriesDone = 0;
                            db.personDAO().insert(oseba1);
                            InsertDataInDB(db);
                        }
                    }.start();

                    //SHARED PREFERENCES
                    //dobimo ustrezno shrambo
                    SharedPreferences sharedPrefs = getSharedPreferences("myshreda",Context.MODE_PRIVATE);

                    //dobimo privzeto vrednost, privzeto je resource ki se shranjuje v values/integers.xml
                    String privzeto = getResources().getString(R.string.privzetEmptyUsername);

                    //dobimo prejsno vrednost stevca, getInt(key, value)
                    String ime = sharedPrefs.getString(getString(R.string.kljuc), privzeto);
                    ime = imePriimek.getText().toString();

                    // editiramo vrednost v ime uporabnika
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString( getString(R.string.kljuc), ime );

                    //izberemo sinhrono shranjevanje ker traja itak tok mal da lahko program pocaka
                    editor.commit();

                    String ime2 = sharedPrefs.getString(getString(R.string.kljuc), privzeto);
                    System.out.println("wwwwwww ime v loginu " + ime2+ ".");

                    //zacnemo main activity
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);

                }

                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();
//      O S E B A
                List<PersonEntity> p = db.personDAO().getAll();
                for (int i = 0; i < p.size(); i++) {
                    System.out.println("****************** OSEBA  id cloveka: " + p.get(i).idPerson+", " + p.get(i).imePriimek);
                }


            }
        });



        //dropdown menu
        String[] arraySpinner = new String[] {
                "Moški", "Ženska", "Drugo"
        };
        s = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);



    }
    public void InsertDataInDB(AppDatabase db){
        //TODO: popravi da je na svoji niti
        System.out.println("šššššš klicemo insert data in db");

        VajaEntity squats = new VajaEntity();
        squats.imeVaje = "Squats";
        squats.muscleG = "Legs";
        squats.imgVaje = R.drawable.ic_overheadpress;
        squats.cals = 20;
        db.vajeDao().insert(squats);

        VajaEntity nadGlavni_vzdig = new VajaEntity();
        nadGlavni_vzdig.imeVaje = "Nadglavni vzdig";
        nadGlavni_vzdig.muscleG = "Ramena";
        nadGlavni_vzdig.imgVaje = R.drawable.ic_overheadpress;
        nadGlavni_vzdig.cals = 20;
        db.vajeDao().insert(nadGlavni_vzdig);

        VajaEntity lundges = new VajaEntity();
        lundges.imeVaje = "Lundges";
        lundges.muscleG = "Legs";
        lundges.imgVaje = R.drawable.ic_overheadpress;
        lundges.cals = 20;
        db.vajeDao().insert(lundges);

        VajaEntity hipThr = new VajaEntity();
        hipThr.imeVaje = "Hip thrusts";
        hipThr.muscleG = "Legs";
        hipThr.imgVaje = R.drawable.ic_overheadpress;
        hipThr.cals = 20;
        db.vajeDao().insert(hipThr);

        VajaEntity good_morning = new VajaEntity();
        good_morning.imeVaje = "Good morning";
        good_morning.muscleG = "Legs";
        good_morning.imgVaje = R.drawable.ic_overheadpress;
        good_morning.cals= 11;
        db.vajeDao().insert(good_morning);

        VajaEntity romanian_deadlift = new VajaEntity();
        romanian_deadlift.imeVaje = "Romanian deadlift";
        romanian_deadlift.muscleG = "Legs";
        romanian_deadlift.imgVaje = R.drawable.ic_overheadpress;
        romanian_deadlift.cals= 33;
        db.vajeDao().insert(romanian_deadlift);

        WorkoutEntity upperBody = new WorkoutEntity();
        upperBody.imeWorkouta = "Upper body";
        upperBody.isCustom = 1;
        upperBody.trajanje = 30;
        upperBody.totalCals = 0;
        upperBody.pripadaOsebi = 1;
        db.workoutDAO().insert(upperBody);

        WorkoutEntity upperBody2 = new WorkoutEntity();
        upperBody2.imeWorkouta = "Upper body 2";
        upperBody2.isCustom = 1;
        upperBody2.trajanje = 40;
        upperBody2.totalCals = 0;
        upperBody2.pripadaOsebi = 1;
        db.workoutDAO().insert(upperBody2);

        WorkoutEntity upperBody3 = new WorkoutEntity();
        upperBody3.imeWorkouta = "Upper body 3";
        upperBody3.isCustom = 1;
        upperBody3.trajanje = 40;
        upperBody3.totalCals = 0;
        upperBody3.pripadaOsebi = 1;
        db.workoutDAO().insert(upperBody3);

        WorkoutEntity leggo = new WorkoutEntity();
        leggo.imeWorkouta = "Leg day";
        leggo.isCustom = 0;
        leggo.trajanje = 30;
        leggo.totalCals = 0;
        leggo.pripadaOsebi = 1;
        db.workoutDAO().insert(leggo);

        WorkoutEntity leggo2 = new WorkoutEntity();
        leggo2.imeWorkouta = "Leg day2";
        leggo2.isCustom = 0;
        leggo2.trajanje = 30;
        leggo2.totalCals = 0;
        leggo2.pripadaOsebi = 1;
        db.workoutDAO().insert(leggo2);


        //povezemo upper body z vsemi vajami ki jih imamo
        for (int i = 0; i < db.vajeDao().getAll().size(); i++) {
            CrossRefVajaWorkout cr = new CrossRefVajaWorkout();
            cr.idWorkouta = 1;
            cr.idVaje = i;
            db.workoutVajeDAO().insert(cr);

        }


//    D E T A I L S
        DetailsEntity prviSet = new DetailsEntity();
        prviSet.pripadaVaji = 2;
        prviSet.pripadaWorkoutu = 1;
        prviSet.setNo = 1;
        prviSet.reps = 10;
        prviSet.weight = 400;
        db.detailsDAO().insert(prviSet);

//        DetailsEntity ds = new DetailsEntity();
//        ds.pripadaVaji = 2;
//        ds.pripadaWorkoutu = 2;
//        ds.setNo = 2;
//        ds.reps = 9;
//        ds.weight = 300;
//        db.detailsDAO().insert(ds);
//
//        DetailsEntity ts = new DetailsEntity();
//        ts.pripadaVaji = 2;
//        ts.pripadaWorkoutu = 2;
//        ts.setNo = 3;
//        ts.reps = 4;
//        ts.weight = 200;
//        db.detailsDAO().insert(ts);

//        DetailsEntity mmm = new DetailsEntity();
//        mmm.pripadaVaji = 3;
//        mmm.pripadaWorkoutu = 2;
//        mmm.setNo = 1;
//        mmm.reps = 10;
//        mmm.weight = 22;
//        db.detailsDAO().insert(mmm);
//
//        DetailsEntity nnn = new DetailsEntity();
//        nnn.pripadaVaji = 2;
//        nnn.pripadaWorkoutu = 1;
//        nnn.setNo = 3;
//        nnn.reps = 13;
//        nnn.weight = 33;
//        db.detailsDAO().insert(nnn);

        // D E T A I L S
//        List<DetailsEntity> d = db.detailsDAO().getAll();
//        for (int i = 0; i < d.size(); i++) {
//           System.out.println("** details pripada wrku:   " + d.get(i).pripadaWorkoutu + " pripada vaji: " + d.get(i).pripadaVaji + " ostalo: " + d.get(i).setNo + " "+ d.get(i).reps +" "+ d.get(i).weight);
//        }
//
//        List<DetailsEntity> dq = db.detailsDAO().findSpecificneDetailse( 2,2);
//        for (int i = 0; i < dq.size(); i++) {
//            System.out.println("** findSpecificniDetail:   " + dq.get(i).pripadaWorkoutu + " pripada vaji: " + dq.get(i).pripadaVaji + " ostalo: " + dq.get(i).setNo + " "+ dq.get(i).reps +" "+ dq.get(i).weight);
//        }
//
//        List<DetailsEntity> dqq = db.detailsDAO().findDetailsOdWorkouta( 2);
//        for (int i = 0; i < dqq.size(); i++) {
//            System.out.println("** vsi detaili od workouta 2:   " + dqq.get(i).pripadaWorkoutu + " pripada vaji: " + dqq.get(i).pripadaVaji + " ostalo: " + dqq.get(i).setNo + " "+ dqq.get(i).reps +" "+ dqq.get(i).weight);
//        }


//        List<WorkoutVaje> wv = db.workoutVajeDAO().getWorkoutVaje();
//        for (int i = 0; i < wv.size(); i++) {
//            for (int j = 0; j < wv.get(i).vajaEntityList.size(); j++) {
//                System.out.println("** povezava wokrouta: " +  wv.get(i).workoutEntity.imeWorkouta + ", z vajami: " + wv.get(i).vajaEntityList.get(j).imeVaje );
//            }
//        }


//      W O R K O U T
        List<WorkoutEntity> w = db.workoutDAO().getAll();
        for (int i = 0; i < w.size(); i++) {
            System.out.println("** id wokrouta: " +  w.get(i).idWorkouta + ", " + w.get(i).imeWorkouta );
        }

//      V A J A
        List<VajaEntity> v = db.vajeDao().getAll();
        for (int i = 0; i < v.size(); i++) {
            System.out.println("** id vaje: " + v.get(i).idVaje+", " + v.get(i).imeVaje);
        }

//      O S E B A
        List<PersonEntity> p = db.personDAO().getAll();
        for (int i = 0; i < p.size(); i++) {
            System.out.println("****************** OSEBA RATATATTATATAT id cloveka: " + p.get(i).idPerson+", " + p.get(i).imePriimek);
        }
    }
}