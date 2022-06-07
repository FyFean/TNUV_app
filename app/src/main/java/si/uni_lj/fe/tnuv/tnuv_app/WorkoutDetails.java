package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database.VajeDAO;

public class WorkoutDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details2);
        Workout myList = getIntent().getParcelableExtra("mylist");




        System.out.println(myList.getIme());

//        getApplicationContext().deleteDatabase("database-name");

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        VajaEntity vaja = new VajaEntity();
        vaja.imeVaje = "novo";
        vaja.muscleG = "Legs";
        vaja.imgVaje = 10;
        db.vajeDao().insert(vaja);
        List<VajaEntity> v = db.vajeDao().getAll();
        for (int i = 0; i < v.size(); i++) {
            System.out.println("** "+v.get(i).imeVaje+", " +v.get(i).id);
        }

        db.vajeDao().delete(v.get(0));
        v = db.vajeDao().getAll();
        for (int i = 0; i < v.size(); i++) {
            System.out.println("**** "+v.get(i).imeVaje);
        }
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