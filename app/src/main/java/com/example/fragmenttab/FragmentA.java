package com.example.fragmenttab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fragmenttab.Adapter.JsonRecycleView;
import com.example.fragmenttab.Json.PojoJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class FragmentA extends Fragment {
    RecyclerView recyclerView;
    ProgressDialog progress;
    JSONArray jsonArray;
    Context context;
    public RequestQueue requestQueue;
    ArrayList<PojoJson> arrayList= new ArrayList<PojoJson>();
    RecyclerView.Adapter adapter;
    String rp="";
    RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragmenta,container,false);
        idSetter(view);
        requestQueue= Volley.newRequestQueue(context);
        parseJson();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    private void parseJson() {
        String url="https://api.myjson.com/bins/1dqeqd";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("jsonzzzz",response.toString());



                //  Toast.makeText(JsonDataMenu.this, "zz", Toast.LENGTH_SHORT).show();

                try {
                    jsonArray=response.getJSONArray("jsondata");
                    JSONObject tmp=jsonArray.getJSONObject(0);
                    rp=tmp.getString("description");

                    // Log.d("array",jsonArray.length()+"");
                    for(int i=0;i<jsonArray.length();++i)
                    {

                        JSONObject obj=jsonArray.getJSONObject(i);
                        // Log.d("abcd",obj.getString("plantId"));
                        PojoJson p=new PojoJson();
                        p.setImageUrl(obj.getString("imageUrl"));
                        p.setDescription(obj.getString("description"));
                        p.setName(obj.getString("name"));
                        p.setPlantId(obj.getString("plantId"));
                        p.setGrowZoneNumber(obj.getInt("growZoneNumber"));
                        p.setWateringInterval(obj.getInt("wateringInterval"));
                        arrayList.add(p);
                        Log.d("abcd",arrayList.get(i).getGrowZoneNumber()+"");
                    }
                    // Log.d("jsondata",obj.getString("plantId"));
                    // test();
                    dly();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("1111json",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void dly() {


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
              //  progress.cancel();

                test();
            }
        }, 1000);
    }
    private void test() {
        Log.d("array1",arrayList.get(3).getImageUrl()+"");
        Log.d("array1",arrayList.get(3).getDescription());
        layoutManager =new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new JsonRecycleView(context,arrayList,getActivity());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
    private void idSetter(View view) {
        recyclerView=view.findViewById(R.id.rv_sub_list);
    }
}
