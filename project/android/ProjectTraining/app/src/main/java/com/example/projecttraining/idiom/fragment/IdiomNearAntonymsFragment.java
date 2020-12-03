package com.example.projecttraining.idiom.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.FlowLayout;
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

        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        List<String> thesaurus = bundle.getStringArrayList("thesaurus");
        List<String> antonym = bundle.getStringArrayList("antonym");

        if(null != thesaurus){
            FlowLayout flowLayout = new FlowLayout(getContext());
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomThesaurus.addView(flowLayout);

            for(int i = 0; i < thesaurus.size(); ++i){
                final Button button = new Button(getContext());
                button.setId(i);
                button.setTextSize(16);
                button.setText(thesaurus.get(i));
                button.setBackgroundResource(R.drawable.black_border);
                button.setPadding(20,20,20,20);
                //添加到布局文件中去
                flowLayout.addView(button);
                //更新界面
                flowLayout.invalidate();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idiomName = button.getText().toString();
                        Intent intent = new Intent();
                        intent.setClass(getContext(), IdiomInfoActivity.class);
                        intent.putExtra("name",idiomName);
                        getContext().startActivity(intent);
                        Toast.makeText(getContext(),idiomName,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            FlowLayout flowLayout = new FlowLayout(getContext());
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomThesaurus.addView(flowLayout);

            TextView textView = new TextView(getContext());
            textView.setText("暂无该成语的近义词");
            textView.setTextSize(18);
            //添加到布局文件中去
            flowLayout.addView(textView);
            //更新界面
            flowLayout.invalidate();
        }

        if(null != antonym){
            FlowLayout flowLayout = new FlowLayout(getContext());
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomAntonym.addView(flowLayout);

            for(int i = 0; i < antonym.size(); ++i){
                final Button button = new Button(getContext());
                button.setId(i);
                button.setTextSize(16);
                button.setText(antonym.get(i));
                button.setBackgroundResource(R.drawable.black_border);
                button.setPadding(20,20,20,20);
                //添加到布局文件中去
                flowLayout.addView(button);
                //更新界面
                flowLayout.invalidate();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idiomName = button.getText().toString();
                        Intent intent = new Intent();
                        intent.setClass(getContext(), IdiomInfoActivity.class);
                        intent.putExtra("name",idiomName);
                        getContext().startActivity(intent);
                        Toast.makeText(getContext(),idiomName,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            FlowLayout flowLayout = new FlowLayout(getContext());
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomAntonym.addView(flowLayout);

            TextView textView = new TextView(getContext());
            textView.setText("暂无该成语的反义词");
            textView.setTextSize(18);
            //添加到布局文件中去
            flowLayout.addView(textView);
            //更新界面
            flowLayout.invalidate();
        }


        return view;
    }


}
