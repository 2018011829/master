package com.example.projecttraining.idiom.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.adapter.IdiomByTypeNameAdapter;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * 2020-11-25
 * 2020-11-26
 * 2020-11-27
 * 2020-11-28
 * 2020-12-7
 * @author lrf
 */
public class ContentFragment extends Fragment implements ScreenShotable {

    private View containerView;
    private Bitmap bitmap;

    private GridView gridView;
    private IdiomByTypeNameAdapter idiomByTypeNameAdapter;
    protected List<String> strList = new ArrayList<>();


    public static ContentFragment newInstance(List<String> strings) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Integer.class.getName(), (ArrayList<String>) strings);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strList = getArguments().getStringArrayList(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.idiom_type_fragment, container, false);

        gridView = rootView.findViewById(R.id.grid_idiom_by_type_name);
        idiomByTypeNameAdapter = new IdiomByTypeNameAdapter(getContext(),strList,R.layout.idiom_gridview_by_type_name);
        gridView.setAdapter(idiomByTypeNameAdapter);

        return rootView;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

