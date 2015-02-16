package net.cattaka.android.learnlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import net.cattaka.android.learnlayout.fragment.MyListFragment;

/**
 * Created by cattaka on 14/11/19.
 */
public class HeaderedViewPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headered_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        {   // Creating adapter of fragments
            final MyListFragment[] fragments = new MyListFragment[]{
                    new MyListFragment(),
                    new MyListFragment(),
                    new MyListFragment(),
                    new MyListFragment(),
            };
            FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public int getCount() {
                    return fragments.length;
                }

                @Override
                public Fragment getItem(int i) {
                    return fragments[i];
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return "Page:"+position;
                }
            };
            mViewPager.setAdapter(adapter);
        }
    }
}
