package com.automotive.automotiveplatform.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.automotive.automotiveplatform.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import model.MapModel;

public class DashboardFragment extends Fragment implements OnMapReadyCallback {
private GoogleMap mMap;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, null, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;

            }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<MapModel> latLngs = new ArrayList<>();
        latLngs.add(new MapModel(27.7052354, 85.3294158, "Marruti Parts Shop"));
        latLngs.add(new MapModel(27.70482, 86.3293990, "Hond Parts Shop"));

        CameraUpdate center, zoom;
        for (int i = 0; i < latLngs.size(); i++) {
            center =
                    CameraUpdateFactory.newLatLng(new LatLng(latLngs.get(i).getLat(),
                            latLngs.get(i).getLon()));
            zoom = CameraUpdateFactory.zoomTo(16);
            mMap.addMarker(new MarkerOptions().position(new LatLng(latLngs.get(i).getLat(),
                    latLngs.get(i).getLon())).title(latLngs.get(i).getMarker()));

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }
}
