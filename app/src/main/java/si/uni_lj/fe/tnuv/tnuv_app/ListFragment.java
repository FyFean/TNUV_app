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
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    private RecyclerView recyclerView;

    ArrayList<Vaja> listVaj = new ArrayList<Vaja>();

    //tega ustvarimo zato ker moramo v popUp window dat tudi custom workoute, torej v VajaAdapter posiljamo se listWorkoutov
    ArrayList<Workout> listWorkoutov = new ArrayList<Workout>();

    VajaAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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

    //v onStart se povezemo z bazo da dobimo vse vaje in updatamo adapter
    @Override
    public void onStart() {
        super.onStart();

        //thread implements interface runnable ki ima notr metodo run
        new Thread(){

            @Override
            public void run() {
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "database-name").build();

                List<VajaEntity> v = db.vajeDao().getAll();
                List<WorkoutEntity> we = db.workoutDAO().getCustom();

                //vrne podatke v main thread
                getActivity().runOnUiThread( () -> setVajaAdapter(v, we));
            }
        }.start();
    }

    private void setVajaAdapter(List<VajaEntity> vaje, List<WorkoutEntity> we ) {
        for (int i = 0; i < vaje.size(); i++) {
            VajaEntity vajaE = vaje.get(i);
            listVaj.add(new Vaja(vajaE.idVaje, vajaE.imeVaje, vajaE.muscleG, vajaE.imgVaje, vajaE.desc, vajaE.cals));
        }
        for (int i = 0; i < we.size(); i++) {
            WorkoutEntity we_posamezna = we.get(i);

            //nima vaj ker jih ne potrebujemo
            listWorkoutov.add(new Workout(we_posamezna.idWorkouta, we_posamezna.imeWorkouta, we_posamezna.trajanje, we_posamezna.totalCals, null));
        }

        adapter.notifyDataSetChanged();


    }

    //najprej se klice onCreateView zato tuki nastavimo new Adapter in sele ko
    //dobimo podatke iz baze v OnStart updatamo nas adapter.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 0. inflate the layout of this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        // 3. create an adapter
        adapter = new VajaAdapter(listVaj, listWorkoutov,null, 1);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }
}