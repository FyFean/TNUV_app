package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView navigationBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBarView = findViewById(R.id.bottom_nav);

        navigationBarView.setOnItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new WorkoutFragment()).commit();


        navigationBarView.setSelectedItemId(R.id.dumbbell);


    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = item -> {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.list:
                fragment = new ListFragment();
                break;
            case R.id.profile:
                fragment = new HomeFragment();
                break;
            case R.id.dumbbell:
                fragment = new WorkoutFragment();
                break;


        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return true;
    };



    //haha ballers, torej balls, jajcka even, testiculars if u prefer


}