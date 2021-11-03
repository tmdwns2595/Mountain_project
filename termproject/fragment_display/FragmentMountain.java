package org.jjcouple.termproject.fragment_display;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jjcouple.termproject.R;
import org.jjcouple.termproject.mountain_map.CustomAdapter;
import org.jjcouple.termproject.mountain_map.Mountain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class FragmentMountain extends Fragment implements TextWatcher{
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.map_main, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);;
        mList = new ArrayList<>();;

        editText = view.findViewById(R.id.editSearch);
        editText.addTextChangedListener(this);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("dataset/mountain");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
                mList.clear();
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    Mountain mountain1 = snapshot1.getValue(Mountain.class);
                    mList.add(mountain1.getAddress() + " " + mountain1.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", String.valueOf((databaseError.toException())));
            }
        });
        adapter = new CustomAdapter(mList, getContext());
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

