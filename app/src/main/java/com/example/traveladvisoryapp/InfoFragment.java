package com.example.traveladvisoryapp;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements NetworkingService.NetworkingListener {
static  String  code;
    String message;
    double riskscore;
    JsonService jsonService = new JsonService();
    NetworkingService networkingService = new NetworkingService();
    ArrayList<AdvisoryInfo> advisoryInfo = new ArrayList<AdvisoryInfo>(0);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        code = param1;
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            jsonService = ( (myApp)getApplication()).getJsonService();
            //networkingService = ( (myApp)getApplication()).getNetworkingService();
                      //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);
        TextView messagetext = (TextView)view.findViewById(R.id.messagefrag_textView);
        TextView risktext = (TextView)view.findViewById(R.id.riskfrag_textView);
        networkingService.listener =this;
        String x = "Hello***********************************************";
        networkingService.fetchAdvisoryInfo(code);
       // messagetext.setText(getArguments().getString("message1"));
        messagetext.setText(message);
        risktext.setText(riskscore+"");
        return  view;
    }

    @Override
    public void APINetworkListner(String jsonString) {
        String codec = code.toUpperCase();
        advisoryInfo = jsonService.parseAdvisoryInfo(jsonString,codec);
         message = advisoryInfo.get(0).getAdvisoryMessage();
        System.out.println("This is my message from final Fragment@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:" +message);
        riskscore = advisoryInfo.get(0).getRiskScore();
//        InfoFragment fragment = InfoFragment.newInstance(message,riskscore);
    }

    private static InfoFragment newInstance(String message, double riskscore) {
         InfoFragment fragment = new InfoFragment();
//        Bundle args = new Bundle();
//        args.putString("message1", message);
//        args.putDouble("risk", riskscore);
//        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}