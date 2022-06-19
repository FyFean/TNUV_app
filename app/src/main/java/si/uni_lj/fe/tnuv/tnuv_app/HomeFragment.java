package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.DetailsEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    ArrayList<Workout> listWorkoutov = new ArrayList<Workout>();
    WorkoutAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    //tle dodati še nove stvari,vaje majp še opis itd.
//    private void setVajaInfo() {
//        listVaj.add(new Vaja("Squats", "Legs", R.drawable.dumbbell_icon));
//        listVaj.add(new Vaja("Lundges", "Legs", R.drawable.dumbbell_icon));
//        listVaj.add(new Vaja("Hip thrusts", "Legs", R.drawable.dumbbell_icon));
//        listVaj.add(new Vaja("Hip thrusts", "Legs", R.drawable.dumbbell_icon));
//    }


    //v onStart se povezemo z bazo da dobimo vse workoute in updatamo adapter
    @Override
    public void onStart() {
        super.onStart();

        //thread implements interface runnable ki ima notr metodo run
        new Thread(){

            @Override
            public void run() {
                //tole je sam link do baze and as far as i know u can call it multiple times
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "database-name").build();

                //TODO: dodej da bojoj dejansko past
                List<WorkoutEntity> w = db.workoutDAO().getAll();
                List<DetailsEntity> d = db.detailsDAO().getAll();

                for (int i = 0; i < w.size(); i++) {
                    System.out.println("++ tab fragmt workouts: "+w.get(i).imeWorkouta );
                    System.out.println(": "+w.get(i).imeWorkouta );
                }
                //System.out.println("poizvedba " + d);
                for (int i = 0; i < d.size(); i++) {
                    System.out.println("total calories details "+w.get(i).totalCals );
                    System.out.println("poizvedba " + d);
                }

                //vrne podatke v main thread
                getActivity().runOnUiThread( () -> setWorkoutAdapter(w,d));
            }
        }.start();
    }

    private void setWorkoutAdapter(List<WorkoutEntity> w, List<DetailsEntity> d) {

        //loop cez vse custom workoute
        WorkoutEntity we;
        ArrayList<Vaja> list = new ArrayList<Vaja>();
        for (int i = 0; i < w.size(); i++) {
            for (int j = 0; j < d.size(); j++) {
                if (w.get(i).idWorkouta == d.get(j).pripadaWorkoutu) {
                    we = w.get(i);
                    listWorkoutov.add(new Workout(we.idWorkouta, we.imeWorkouta, we.trajanje, we.totalCals, list));
                }
            }


            //dodajanje workouta brez vaj, ker jih v tem primeru ne potrebujeva in baza to zahteva


        }
        adapter.notifyDataSetChanged();
    }




//        listVaj.add(new Vaja("Lundges", "Legs", R.drawable.dumbbell_icon));




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 0. Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        //setVajaInfo();

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        // 3. create an adapter
        adapter = new WorkoutAdapter(listWorkoutov);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}