package com.example.projecttraining.idiom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.activitys.IdiomByTypeActivity;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 2020-11-30
 * 2020-12-2
 * @author lrf
 */
public class IdiomNearAntonymsFragment extends Fragment {

    @BindView(R.id.idiom_antonym) LinearLayout idiomAntonym;
    @BindView(R.id.idiom_thesaurus) LinearLayout idiomThesaurus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.idiom_near_antonyms_fragment,container,false);

        ButterKnife.bind(view);

        Bundle bundle = getArguments();
        List<String> antonym = bundle.getStringArrayList("antonym");
        List<String> thesaurus = bundle.getStringArrayList("thesaurus");

        if(null != antonym){
            for(int i = 0; i < antonym.size(); ++i){
                final Button btn = new Button(getContext());
                btn.setId(i);
                btn.setTextSize(20);
                btn.setText(antonym.get(i));
//                btn.setBackground(R.drawable.black_border);
                // 设置按钮的布局参数
                LinearLayout.LayoutParams buttonParams =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                buttonParams.topMargin = 20;
                idiomAntonym.addView(btn,buttonParams);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idiomName = btn.getText().toString();
                        Intent intent = new Intent();
                        intent.setClass(getContext(), IdiomInfoActivity.class);
                        intent.putExtra("name",idiomName);
                        getContext().startActivity(intent);
                        Toast.makeText(getContext(),idiomName,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {

        }

        if(null != thesaurus){
            for(int i = 0; i < thesaurus.size(); ++i){
                final Button btn = new Button(getContext());
                btn.setId(i);
                btn.setTextSize(20);
                btn.setText(thesaurus.get(i));
//                btn.setBackground(R.drawable.black_border);
                // 设置按钮的布局参数
                LinearLayout.LayoutParams buttonParams =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                buttonParams.topMargin = 20;
                idiomAntonym.addView(btn,buttonParams);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idiomName = btn.getText().toString();
                        Intent intent = new Intent();
                        intent.setClass(getContext(), IdiomInfoActivity.class);
                        intent.putExtra("name",idiomName);
                        getContext().startActivity(intent);
                        Toast.makeText(getContext(),idiomName,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {

        }

        return view;
    }
}
