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
public class IdiomExampleSentenceFragment extends Fragment {

    @BindView(R.id.tv_idiom_example) TextView tvIdiomExample;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.idiom_example_sentence_fragment,container,false);

        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        String example = bundle.getString("example");
        Log.e("lrf_idiom_example",example);

        if(null != example && !example.equals("")){
            tvIdiomExample.setText(example);
        }else {
            tvIdiomExample.setText("暂无该成语的例句");
        }

        return view;
    }
}
