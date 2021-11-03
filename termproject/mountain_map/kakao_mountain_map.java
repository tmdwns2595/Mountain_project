package org.jjcouple.termproject.mountain_map;

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

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import org.jjcouple.termproject.R;

import java.io.IOException;
import java.util.List;

public class kakao_mountain_map extends AppCompatActivity {
    TextView m_text;
    Button uriBtn;
    public String st_lat;
    public String st_lon;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geo_activity);

        m_text = findViewById(R.id.mountain_address);
        uriBtn = findViewById(R.id.web_kakao);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        Intent intent = getIntent();
        String address = intent.getStringExtra("mountain_address");

        m_text.setText(address);

//        Toast.makeText(this, address, Toast.LENGTH_LONG).show();

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
                mapPOIItem.setItemName("목적지 위치");
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
    }
}
