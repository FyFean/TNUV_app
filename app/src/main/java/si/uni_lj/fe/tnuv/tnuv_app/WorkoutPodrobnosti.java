package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WorkoutPodrobnosti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_podrobnosti);
        Workout myList = getIntent().getParcelableExtra("mylist");




        //System.out.println(myList.getIme());



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
}