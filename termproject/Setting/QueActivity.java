package org.jjcouple.termproject.Setting;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jjcouple.termproject.R;

import java.util.ArrayList;

public class QueActivity extends AppCompatActivity {


    RecyclerImageTextAdapter mAdapter ;
    ArrayList<RecyclerItem> mList ;

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        if(getSupportActionBar() != null) {
            ActionBar ab = getSupportActionBar() ;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ab.setTitle("자주 묻는 질문") ;
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler1);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager) ;
        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.

        mList = new ArrayList<>();
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        addItem(getResources().getDrawable(R.drawable.question),"지도상에서 등산 코스 구간별 소요 시간은 어떻게 확인하나요?","안녕하세요.고객님.오늘도 저희 어플을 사용해주셔서 감사합니다.\n지도를 조금 확대하시면 구간별 등산, 하행 시간이 나옵니다.\n앞으로 더 좋은 서비스로 찾아뵙겠습니다.");
        addItem(getResources().getDrawable(R.drawable.question),"캠핑장 정보에 요금이나 이용시간이 안나와있는 경우도 있던데 어떻게 확인하나요?","안녕하세요.고객님.오늘도 저희 어플을 사용해주셔서 감사합니다.\n그런 경우에는 오른쪽 상단에 보시면 전화연결 버튼이 있습니다. 클릭하셔서 업체에 문의주시면 감사하겠습니다.\n앞으로 더 좋은 서비스로 찾아뵙겠습니다.");
        addItem(getResources().getDrawable(R.drawable.question),"계정을 여러개 생성할 수 있나요?","안녕하세요.고객님.오늘도 저희 어플을 사용해주셔서 감사합니다.\n고객님께서 실제 이메일 계정이 여러개가 있으시다면 가능합니다.\n앞으로 더 좋은 서비스로 찾아뵙겠습니다.");

        mAdapter.notifyDataSetChanged();
    }
    public void addItem(Drawable icon,String title, String desc){
        RecyclerItem item = new RecyclerItem();
        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mList.add(item);
    }


}