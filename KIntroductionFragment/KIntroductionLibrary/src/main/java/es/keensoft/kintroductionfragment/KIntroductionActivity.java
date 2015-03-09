package es.keensoft.kintroductionfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public abstract class KIntroductionActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    private final String TAG = "KIntroduction";


    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private LinearLayout layoutDots;
    private List<ImageView> dotsArray = new ArrayList<>();
    private Drawable unseledDrawable = null;
    private LinearLayout container = null;
    private Drawable seletedDrawable = null;
    private List<Fragment> fragmentsToDisplay = new ArrayList<>();
    private DataToDisplay dataToDisplay = null;


   public abstract void setFragmentsData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_introduction);

        setFragmentsData();


        if (dataToDisplay==null) {
            throw new RuntimeException(new String("No data configured"));
        }

        try {
            if (dataToDisplay.getFragmentList().size() != dataToDisplay.getFilmList().size()) {
                throw new RuntimeException(new String("FragmentList and FilmList must have same size"));
            }
        } catch (Exception e) {
            throw new RuntimeException(new String("Invalid fragments"));
        }

        if (dataToDisplay.getFragmentList() != null) {
            fragmentsToDisplay = dataToDisplay.getFragmentList();
        } else {
            throw new RuntimeException(new String("FragmentList can't be null"));
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(this);

        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        container = (LinearLayout) findViewById(R.id.container);

        FrameLayout containerFrame = (FrameLayout) findViewById(R.id.containerFrame);

        try {
            Bitmap myImage = BitmapFactory.decodeResource(getResources(), dataToDisplay.getBackGroundResource());
            Drawable dr = new BitmapDrawable(myImage);
            containerFrame.setBackgroundDrawable(dr);
        } catch (Exception e) {
            throw new RuntimeException(new String("BackgroundDrawable not valid"));
        }
        try {
            container.setBackgroundColor(dataToDisplay.getFilmList().get(0));
        } catch (Exception e) {
            throw new RuntimeException(new String("setBackgroundColor not valid"));
        }

        createDots();

    }

    private void createDots() {

        float density = getResources().getDisplayMetrics().density;
        unseledDrawable = getResources().getDrawable(R.drawable.circle);


        unseledDrawable.setColorFilter(getResources().getColor(R.color.dot_off), PorterDuff.Mode.SRC_ATOP);

        seletedDrawable = getResources().getDrawable(R.drawable.circle);
        seletedDrawable.setColorFilter(getResources().getColor(R.color.dot_on), PorterDuff.Mode.SRC_ATOP);

        for (int i = 0; i < fragmentsToDisplay.size(); i++) {
            ImageView imageView = new ImageView(this);
            //setting image resource
            if (Build.VERSION.SDK_INT >= 16) {
                imageView.setBackground(i == 0 ? seletedDrawable : unseledDrawable);
            } else {
                imageView.setBackgroundDrawable(i == 0 ? seletedDrawable : unseledDrawable);
            }

            int dotSize = (int) (getResources().getDimension(R.dimen.dot_size) * density);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dotSize, dotSize);
            imageView.setLayoutParams(layoutParams);
            int paddingDp = (int) (getResources().getDimension(R.dimen.dot_margin) * density);

            layoutParams.setMargins(paddingDp, paddingDp, paddingDp, paddingDp);

            ViewGroup.MarginLayoutParams lpimgFooter = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            lpimgFooter.bottomMargin = paddingDp;
            lpimgFooter.leftMargin = paddingDp;
            lpimgFooter.rightMargin = paddingDp;
            imageView.setLayoutParams(lpimgFooter);
            imageView.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);


            dotsArray.add(imageView);
            layoutDots.addView(imageView);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);


        }

        @Override
        public Fragment getItem(int position) {

            return fragmentsToDisplay.get(position);
        }


        @Override
        public int getCount() {
            return fragmentsToDisplay.size();
        }


    }



    @Override
    public void onPageSelected(int i) {
        for (int y = 0; y < dotsArray.size(); y++) {
            if (Build.VERSION.SDK_INT >= 16) {
                dotsArray.get(y).setBackground(unseledDrawable);
            } else {
                dotsArray.get(y).setBackgroundDrawable(unseledDrawable);
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            dotsArray.get(i).setBackground(seletedDrawable);
        } else {
            dotsArray.get(i).setBackgroundDrawable(seletedDrawable);
        }

        try {
            container.setBackgroundColor(dataToDisplay.getFilmList().get(i));
        } catch (Exception e) {
            throw new RuntimeException(new String("setBackgroundColor not valid"));
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public DataToDisplay getDataToDisplay() {
        return dataToDisplay;
    }

    public void setDataToDisplay(DataToDisplay dataToDisplay) {
        this.dataToDisplay = dataToDisplay;
    }
}
