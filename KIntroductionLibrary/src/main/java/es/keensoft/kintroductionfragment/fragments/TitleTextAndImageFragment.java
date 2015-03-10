package es.keensoft.kintroductionfragment.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.keensoft.kintroductionfragment.R;


public class TitleTextAndImageFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_TEXT = "text";
    private static final String ARG_IMAGE = "image";
    private static final String TAG = "TitleAndText";

    private int title;
    private int text;
    private int image;


    public static TitleTextAndImageFragment newInstance(int title, int text, int image) {
        TitleTextAndImageFragment fragment = new TitleTextAndImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, title);
        args.putInt(ARG_TEXT, text);
        args.putInt(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    public TitleTextAndImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getInt(ARG_TITLE);
            text = getArguments().getInt(ARG_TEXT);
            image = getArguments().getInt(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.title_text_and_image, container, false);
        TextView titleTV = (TextView) v.findViewById(R.id.title);
        titleTV.setText(Html.fromHtml(getString(title)));
        TextView textTV = (TextView) v.findViewById(R.id.text);
        textTV.setText(Html.fromHtml(getString(text)));
        textTV.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setBackgroundResource(image);
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
