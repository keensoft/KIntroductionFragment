package es.keensoft.demo;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import es.keensoft.kintroductionfragment.DataToDisplay;
import es.keensoft.kintroductionfragment.KIntroductionActivity;
import es.keensoft.kintroductionfragment.fragments.TitleAndTextFragment;
import es.keensoft.kintroductionfragment.fragments.TitleTextAndImageFragment;


public class MainActivity extends KIntroductionActivity {

    @Override
    public void setFragmentsData() {
        DataToDisplay data = new DataToDisplay();

        List<Fragment> fragmentList = new ArrayList<>();

        //Title text and image
        fragmentList.add(
                TitleTextAndImageFragment.newInstance(
                        R.string.title1,
                        R.string.text3,
                        R.drawable.logokeen
                )
        );
        //Title and text
        fragmentList.add(
                TitleAndTextFragment.newInstance(
                        R.string.title2,
                        R.string.text1
                )
        );
        //Title and text
        fragmentList.add(
                TitleAndTextFragment.newInstance(
                        R.string.title3,
                        R.string.text2
                )
        );
        //Title and text
        fragmentList.add(
                TitleAndTextFragment.newInstance(
                        R.string.title4,
                        R.string.text1
                )
        );

        List<Integer> filmList = new ArrayList<>();
        filmList.add(Color.parseColor("#665ec5ed"));
        filmList.add(Color.parseColor("#660e4a61"));
        filmList.add(Color.parseColor("#6671d4f9"));
        filmList.add(Color.parseColor("#660e4a61"));

        data.setFilmList(filmList);
        data.setFragmentList(fragmentList);
        //to set background image
        //data.setBackGroundResource();
        setDataToDisplay(data);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

}
