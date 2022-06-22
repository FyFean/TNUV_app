package si.uni_lj.fe.tnuv.tnuv_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {

    public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    //set child fragments s switchom
    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new TabFragment1();

        switch (position){
            case 0:
                fragment = new TabFragment1();
                break;
            case 1:
                fragment = new TabFragment2();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    //za imena tabov
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Po meri";
        }
        else if (position == 1)
        {
            title = "Pripravljeni";
        }
        return title;
    }
}
