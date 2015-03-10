KIntroductionFragment
======================

#####NOTICE:

Initial version 0.5.0

## Built for your app

Introductions library for creating aids or aids paginated in the format specified by the user or using available formats Bookseller.

**Features Include**
* General background
* Predefined Fragments
  * Title and text
  * Title, text and image
* Custom Film on each page

## What to Include

### Manual Installation
Download the source code and include as module in your proyect. Then add to the dependencies:
```java
dependencies {
    compile project(':KIntroductionLibrary')
}
```


## The Process

To use create java class Test and extend from KIntroductionActivity, them add the reference in your manifest

```xml
        <activity
            android:name=".Test"
            android:label="@string/title_activity" >
        </activity>

```

Now you can add your fragments:
```java
package es.keensoft.smartherapy;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import es.keensoft.kintroductionfragment.DataToDisplay;
import es.keensoft.kintroductionfragment.KIntroductionActivity;
import es.keensoft.kintroductionfragment.fragments.TitleAndTextFragment;
import es.keensoft.kintroductionfragment.fragments.TitleTextAndImageFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Test extends KIntroductionActivity {
    @Override
    public void setFragmentsData() {

        DataToDisplay data = new DataToDisplay();

        List<Fragment> fragmentList = new ArrayList<>();

        //Title and text
        fragmentList.add(
                TitleAndTextFragment.newInstance(
                        R.string.help_firstDescTitle, 
                        R.string.help_firstDesc
                )
        );
        //Title text and image
        fragmentList.add(
                TitleTextAndImageFragment.newInstance(
                        R.string.help_firstDescTitle, 
                        R.string.help_firstDesc, 
                        R.mipmap.ic_launcher
                )
        );

        List<Integer> filmList = new ArrayList<>();
        filmList.add(Color.parseColor("#66F5F6CE"));
        filmList.add(Color.parseColor("#66F5F6CE"));


        data.setFilmList(filmList);
        data.setFragmentList(fragmentList);
        data.setBackGroundResource(R.drawable.helpbg);
        setDataToDisplay(data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

```
If you need add more code you can override methods to give new features.


______
![enter image description here][1]

Contact: [info@keensoft.es][2]

Web: [http://keensoft.es][3]


[1]: http://www.keensoft.es/wp-content/uploads/2013/04/keensoft-logo1.png
[2]: info@keensoft.es
[3]: http://keensoft.es