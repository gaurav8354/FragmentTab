package com.example.fragmenttab;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.fragmenttab.Json.PojoJson;

public class FragmentB extends Fragment {
    Context cxt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentb,container,false);
        TextView head,grownumber,watering,scrool;
        ImageView imageView=view.findViewById(R.id.imageView_final);
        head=view.findViewById(R.id.tv_final_name);
        grownumber=view.findViewById(R.id.tv_growzonenumber);
        watering=view.findViewById(R.id.tv_wateringInterval);
        scrool=view.findViewById(R.id.tv_final_discription);

Bundle bundle=getArguments();
if(bundle!=null)
{
    PojoJson pojoJson= (PojoJson) bundle.getSerializable("data");
    head.setText(pojoJson.getName());
    grownumber.setText(grownumber.getText().toString()+" : "+pojoJson.getGrowZoneNumber());
    watering.setText(watering.getText().toString()+" : "+pojoJson.getWateringInterval());
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        scrool.setText(Html.fromHtml(pojoJson.getDescription(), Html.FROM_HTML_MODE_LEGACY));
    } else {
        scrool.setText(Html.fromHtml(pojoJson.getDescription()));
    }
    Glide.with(cxt).load(pojoJson.getImageUrl()).into(imageView);

}

        return view;
    }

    @Override
    public void onAttach(Context context) {
        cxt=context;
        super.onAttach(context);
    }
}
