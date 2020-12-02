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
public class IdiomMeanFragment extends Fragment {

    @BindView(R.id.tv_idiom_content) TextView tvIdiomContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.idiom_mean_fragment,container,false);

        ButterKnife.bind(view);

        Bundle bundle = getArguments();
        String content = bundle.getString("content");
        Log.e("lrf_idiom_mean",content);
        if(null != content){
            tvIdiomContent.setText(content);
        }else {
            tvIdiomContent.setText("暂无该成语的解释");
        }

        return view;
    }
}
