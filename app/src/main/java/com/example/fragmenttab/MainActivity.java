package com.example.fragmenttab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fragmenttab.Json.PojoJson;
import com.example.fragmenttab.interface1.Communicate;

public class MainActivity extends AppCompatActivity implements Communicate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        FragmentA fragmentA=new FragmentA();
        FragmentB fragmentB=new FragmentB();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framea,fragmentA,null);
        fragmentTransaction.add(R.id.frameb,fragmentB,null);
        fragmentTransaction.commit();

    }


    @Override
    public void dataSender(PojoJson obj) {
        //Toast.makeText(this, obj.getImageUrl(), Toast.LENGTH_SHORT).show();
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",obj);
        FragmentB fragmentB=new FragmentB();
        fragmentB.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameb,fragmentB,null);
        fragmentTransaction.commit();
    }
}
