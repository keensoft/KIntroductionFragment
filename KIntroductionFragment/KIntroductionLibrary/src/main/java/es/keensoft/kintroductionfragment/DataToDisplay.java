package es.keensoft.kintroductionfragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class DataToDisplay implements Parcelable {

    private List<Fragment> fragmentList;
    private List<Integer> filmList;
    private int backGroundResource;

    public DataToDisplay() {
    }

    protected DataToDisplay(Parcel in) {
        if (in.readByte() == 0x01) {
            fragmentList = new ArrayList<Fragment>();
            in.readList(fragmentList, Fragment.class.getClassLoader());
        } else {
            fragmentList = null;
        }
        if (in.readByte() == 0x01) {
            filmList = new ArrayList<Integer>();
            in.readList(filmList, Integer.class.getClassLoader());
        } else {
            filmList = null;
        }
        backGroundResource = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (fragmentList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(fragmentList);
        }
        if (filmList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(filmList);
        }
        dest.writeInt(backGroundResource);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DataToDisplay> CREATOR = new Parcelable.Creator<DataToDisplay>() {
        @Override
        public DataToDisplay createFromParcel(Parcel in) {
            return new DataToDisplay(in);
        }

        @Override
        public DataToDisplay[] newArray(int size) {
            return new DataToDisplay[size];
        }
    };

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public List<Integer> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Integer> filmList) {
        this.filmList = filmList;
    }

    public int getBackGroundResource() {
        return backGroundResource;
    }

    public void setBackGroundResource(int backGroundResource) {
        this.backGroundResource = backGroundResource;
    }
}
