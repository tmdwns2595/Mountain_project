package org.jjcouple.termproject.fragment_display;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jjcouple.termproject.calendar.CalendarActivity;
import org.jjcouple.termproject.R;
import org.jjcouple.termproject.notice_display.noticeActivity;

public class FragmentHome extends Fragment {

    public FragmentHome(){ }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_activity, container, false);
        ImageButton img1 = (ImageButton)view.findViewById(R.id.imageButton1);
        ImageButton img2 = (ImageButton)view.findViewById(R.id.imageButton2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "일정관리가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "등산 유의사항이 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), noticeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}