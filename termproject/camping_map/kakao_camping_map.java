package org.jjcouple.termproject.camping_map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jjcouple.termproject.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class kakao_camping_map extends AppCompatActivity {
    TextView m_text;
    Button uriBtn;
    Button infoBtn;
    TextView infoText;
    public String tel;
    public String st_lat;
    public String st_lon;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private HashMap<String, String> camp_info = new HashMap<String, String>();
    private HashMap<String, String> camp_tel = new HashMap<String, String>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camping_geo_activity);

        m_text = findViewById(R.id.mountain_address);
        uriBtn = findViewById(R.id.web_kakao);
        infoBtn = findViewById(R.id.camp_info);
        infoText = findViewById(R.id.text_info);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        Intent intent = getIntent();
        String address = intent.getStringExtra("camping_address");

        m_text.setText(address);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("dataset/records");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrayList.clear();
                for (DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    records record = snapshot1.getValue(records.class);
                    if(record.getAddress2() == null && record.getAddress1() != null){
                        Log.d("로그 출력 --> ", record.getCamping_time() + ", " + record.getCamping_fee());
                        if(record.getCamping_time() == null && record.getCamping_fee() == null) {
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() == null && record.getCamping_fee() != null){
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() != null && record.getCamping_fee() == null){
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else{
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                    }

                    else if(record.getAddress1() == null && record.getAddress2() != null){
                        Log.d("로그 출력 --> ", record.getCamping_time() + ", " + record.getCamping_fee());
                        if(record.getCamping_time() == null && record.getCamping_fee() == null) {
                            camp_info.put(record.getAddress2() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() == null && record.getCamping_fee() != null){
                            camp_info.put(record.getAddress2() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() != null && record.getCamping_fee() == null){
                            camp_info.put(record.getAddress2() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else{
                            camp_info.put(record.getAddress2() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                    }
                    else if(record.getAddress2() != null && record.getAddress1() != null){
                        Log.d("로그 출력 --> ", record.getCamping_time() + ", " + record.getCamping_fee());
                        if(record.getCamping_time() == null && record.getCamping_fee() == null) {
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() == null && record.getCamping_fee() != null){
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : 업체문의" + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else if(record.getCamping_time() != null && record.getCamping_fee() == null){
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : 업체문의");
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                        else{
                            camp_info.put(record.getAddress1() + " " + record.getName(), "캠핑장명 : " + record.getName() + "\n" + "도로명 주소 : " + record.getAddress1() + "\n" + "지번주소 : "
                                    + record.getAddress2() + "\n" + "전화번호 : " + record.getCamping_num() + "\n" + "편의시설 : " + record.getCamping_comb() + "\n" + "안전시설 : "
                                    + record.getCamping_safe() + "\n" + "이용시간 : " + record.getCamping_time() + "\n" + "이용요금 : " + record.getCamping_fee());
                            camp_tel.put(record.getAddress1() + " " + record.getName(), record.getCamping_num());
                        }
                    }
                }
                infoText.setText(camp_info.get(address));
                tel = "tel:" + camp_tel.get(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MtList2", String.valueOf((databaseError.toException())));
            }
        });

//        Toast.makeText(this, camp_info.get(address), Toast.LENGTH_LONG).show();

        final Geocoder geocoder = new Geocoder(this);

        List<Address> list = null;

        String str = address;
        try {
            list = geocoder.getFromLocationName
                    (str, // 지역 이름
                            10); // 읽을 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }

        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(this, "해당 주소의 위도, 경도값을 구할 수 없습니다.", Toast.LENGTH_LONG);
            } else {
                Address addr = list.get(0);
                double lat = addr.getLatitude();
                double lon = addr.getLongitude();

                st_lat = String.valueOf(lat);
                st_lon = String.valueOf(lon);

//                String sss1 = String.format("%f", lat);
//                String sss2 = String.format("%f", lon);

                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lon), true);
                MapPOIItem mapPOIItem = new MapPOIItem();
                mapPOIItem.setItemName("캠핑장 정보");
                mapPOIItem.setTag(0);
                mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(lat, lon));
                mapPOIItem.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
                mapPOIItem.setDraggable(true);// true = 핀을 꾸욱 눌러서 이동시킬 수 있습니다. default : false
                mapView.addPOIItem(mapPOIItem);
            }
        }

        uriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri url = Uri.parse("https://map.kakao.com/link/to/" + address + "," + st_lat + "," + st_lon);
                Log.d("Kakao_mountain_map", String.valueOf(url));
                Intent urlintent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(urlintent);
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
    }
}
