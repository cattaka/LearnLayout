package net.cattaka.android.learnlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import net.cattaka.android.learnlayout.fragment.BlankFragment;
import net.cattaka.android.learnlayout.transformer.ZoomOutPageTransformer;

/**
 * Created by cattaka on 14/11/19.
 */
public class ViewPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        {   // Creating adapter of fragments
            final BlankFragment[] fragments = new BlankFragment[]{
                    new BlankFragment(),
                    new BlankFragment(),
                    new BlankFragment(),
                    new BlankFragment(),
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
            };
            mViewPager.setAdapter(adapter);
        }
        {
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        }
    }
}
