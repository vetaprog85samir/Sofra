package com.food.sofra.adapter;




import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerWithFragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentsTitle = new ArrayList<>();

    public ViewPagerWithFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments = new ArrayList<>();
        fragmentsTitle = new ArrayList<>();


    }

    public void addPager(Fragment fragments, String fragmentTitle) {
        this.fragments.add(fragments);
        this.fragmentsTitle.add(fragmentTitle);
    }

    // Returns cost number of pages
    @Override
    public int getCount() {
        return fragments.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitle.get(position);
    }
}
