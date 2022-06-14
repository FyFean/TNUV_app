package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    ArrayList<Vaja> listVaj = new ArrayList<Vaja>();

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

    private void setVajaInfo() {
        listVaj.add(new Vaja("Squats", "Legs", R.drawable.dumbbell_icon));
        listVaj.add(new Vaja("Lundges", "Legs", R.drawable.dumbbell_icon));
        listVaj.add(new Vaja("Hip thrusts", "Legs", R.drawable.dumbbell_icon));
        listVaj.add(new Vaja("Hip thrusts", "Legs", R.drawable.dumbbell_icon));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 0. Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        // 1. get a reference to recyclerView
        recyclerView = rootView.findViewById(R.id.recyclerView);
        setVajaInfo();

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        // 3. create an adapter
        VajaAdapter adapter = new VajaAdapter(listVaj);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}