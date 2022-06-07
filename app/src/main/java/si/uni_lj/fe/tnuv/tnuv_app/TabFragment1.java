package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.ListViewAutoScrollHelper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<Workout> listWorkoutov = new ArrayList<Workout>();


    private void setVajaInfo() {

        ArrayList<Vaja> listVaj = new ArrayList<Vaja>();
        listVaj.add(new Vaja("Squats", "Legs", R.drawable.dumbbell_icon));
        listVaj.add(new Vaja("Lundges", "Legs", R.drawable.dumbbell_icon));
        listVaj.add(new Vaja("Hip thrusts", "Legs", R.drawable.dumbbell_icon));

        listWorkoutov.add(new Workout("Leg day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));
        listWorkoutov.add(new Workout("Booty day",listVaj ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment1, container, false);

        // 1. get a reference to recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        setVajaInfo();

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // 3. create an adapter
        WorkoutAdapter adapter = new WorkoutAdapter(listWorkoutov);

        // 4. set adapter
        recyclerView.setAdapter(adapter);

        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;

    }
}
