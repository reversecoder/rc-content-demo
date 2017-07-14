package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.ViewPagerAdapter;
import com.reversecoder.content.demo.fragment.InstalledAppFragment;
import com.reversecoder.content.demo.fragment.UnusedAppFragment;

/**
 * @author Md. Rashadul Alam
 */
public class ApplicationsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private Fragment fragmentInstalledApps;
    private Fragment fragmentUnusedApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_tab);
        initUI();
    }

    private void initUI() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        initializeFragments();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentInstalledApps, getString(R.string.fragment_title_installed_app));
        viewPagerAdapter.addFragment(fragmentUnusedApps, getString(R.string.fragment_title_unused_app));
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initializeFragments() {
        fragmentInstalledApps = InstalledAppFragment.newInstance();
        fragmentUnusedApps = UnusedAppFragment.newInstance();
    }

    //Return current fragment on basis of Position
    public Fragment getFragment(int pos) {
        return viewPagerAdapter.getItem(pos);
    }
}
