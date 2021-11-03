package org.jjcouple.termproject.fragment_display;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jjcouple.termproject.R;
import org.jjcouple.termproject.camping_map.CustomAdapter2;
import org.jjcouple.termproject.camping_map.records;

import java.util.ArrayList;
import java.util.List;

public class FragmentCamping extends Fragment implements TextWatcher {
    private RecyclerView recyclerView;
    private CustomAdapter2 adapter;
    private RecyclerView.LayoutManager layoutManager;
    //    private ArrayList<records> arrayList;
    private ArrayList<String> mList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.camping_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<>();
        mList = new ArrayList<>();

        editText = view.findViewById(R.id.editSearch);
        editText.addTextChangedListener(this);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("dataset/records");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
                mList.clear();
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    records record = snapshot1.getValue(records.class);
                    if(record.getAddress2() == null && record.getAddress1() != null){
                        mList.add(record.getAddress1() + " " + record.getName());
                        Log.d("MList2", record.getAddress1() + " " + record.getName());
                    }
                    else if(record.getAddress1() == null && record.getAddress2() != null){
                        mList.add(record.getAddress2() + " " + record.getName());
                        Log.d("MList2",record.getAddress2() + " " + record.getName());
                    }
                    else if(record.getAddress1() != null && record.getAddress2() != null){
                        mList.add(record.getAddress1() + " " + record.getName());
                        Log.d("MList2", record.getAddress1() + " " + record.getName());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MtList2", String.valueOf((databaseError.toException())));
            }
        });
        adapter = new CustomAdapter2(mList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((View v, int position) -> {

        }) ;
        return view;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
