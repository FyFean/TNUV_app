package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;

public class TabFragment1 extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Workout> listWorkoutov = new ArrayList<Workout>();
    WorkoutAdapter adapter;



    //v onStart se povezemo z bazo da dobimo vse workoute in updatamo adapter
    //onStart se pozene za onCreate in onCreateView
    @Override
    public void onStart() {
        System.out.println("mm takoj v OnStartu: " + listWorkoutov);
        super.onStart();

        //onStart je klicana ko gremo v novo aktivnost workoutPodrobnosti, a pred tem ne klice OnCreateView torej je list se poln prejsnih elementov, zato jih zbrisemo in sam vsakic na nov povezujemo
        listWorkoutov.clear();

            //thread implements interface runnable ki ima notr metodo run
            new Thread(){

                @Override
                public void run() {
                    //tole je sam link do baze and as far as i know u can call it multiple times
                    AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "database-name").build();

                    List<WorkoutEntity> w = db.workoutDAO().getCustom();

                    for (int i = 0; i < w.size(); i++) {
                        System.out.println("++ tab fragmt workouts: "+w.get(i).imeWorkouta );
                    }

                    List<WorkoutVaje> wv = db.workoutVajeDAO().getWorkoutVaje();
                    for (int i = 0; i < wv.size(); i++) {
                        for (int j = 0; j < wv.get(i).vajaEntityList.size(); j++) {
                            System.out.println("++ tab fragmt povezava wokrouta: " +  wv.get(i).workoutEntity.imeWorkouta + ", z vajami: " + wv.get(i).vajaEntityList.get(j).imeVaje );
                        }
                    }
                    //vrne podatke v main thread
                    getActivity().runOnUiThread( () -> setWorkoutAdapter(w, wv));
                }
            }.start();
//
    }

    private void setWorkoutAdapter(List<WorkoutEntity> w, List<WorkoutVaje> wv ) {

        //loop cez vse custom workoute
        for (int i = 0; i < w.size(); i++) {
            WorkoutEntity we = w.get(i);

            //loop cez vse povezave workoutov z vajami
            for (int j = 0; j < wv.size(); j++) {

                //ce najdemo pravi workout loopamo naprej cez vse vaje tega workouta
                if(wv.get(j).workoutEntity.idWorkouta == we.idWorkouta){
                    ArrayList<Vaja> listVaj = new ArrayList<Vaja>();
                    for (int k = 0; k < wv.get(j).vajaEntityList.size(); k++) {
                        VajaEntity v = wv.get(j).vajaEntityList.get(k);
                        listVaj.add(new Vaja(v.idVaje, v.imeVaje, v.muscleG, R.drawable.dumbbell_icon, v.desc, v.cals, v.recomendedReps, v.recommendedSets));
//                        TODO: calculateTotalCals();
                    }
                    listWorkoutov.add(new Workout(we.idWorkouta, we.imeWorkouta, we.trajanje, we.totalCals, listVaj));
                }

            }
        }

        System.out.println("mm ko vrnemo podatke v mainThr list: " + listWorkoutov);
        //holda reference na list ki ga posljes v adapter in opazuje ce se je ta list spremenil.
        //Ko se naloada onStart in list dobi podatke (prej je biu prazen), adapter avtomatsko poserje podatke notr.
        adapter.notifyDataSetChanged();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment1, container, false);

        // 1. get a reference to recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // 3. create an adapter
        adapter = new WorkoutAdapter(listWorkoutov);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        view.findViewById(R.id.StartWrk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmptyWorkout.class);
                startActivity(intent);
            }
        });

        return view;

    }
}
