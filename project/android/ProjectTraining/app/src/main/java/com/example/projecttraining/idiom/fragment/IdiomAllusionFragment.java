package com.example.projecttraining.idiom.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 2020-11-30
 * 2020-12-2
 * @author lrf
 */
public class IdiomAllusionFragment extends Fragment {

    @BindView(R.id.tv_idiom_comeform) TextView tvIdiomComefrom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.idiom_allusion_fragment,container,false);

        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        String comefrom = bundle.getString("comefrom");
        Log.e("lrf_idiom_comefrom",comefrom);

        if(null != comefrom && !comefrom.equals("null")){
            tvIdiomComefrom.setText(comefrom);
        }else {
            tvIdiomComefrom.setText("暂无该成语的典故出处");
        }

        return view;
    }
}
