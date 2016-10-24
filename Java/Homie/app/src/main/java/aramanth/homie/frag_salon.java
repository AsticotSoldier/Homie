package aramanth.homie;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_salon.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_salon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_salon extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Inflater inflater;

    private BluetoothAdapter BLE_adaptater;
    private BluetoothLeScanner BLE_scan;
    private BluetoothGatt mGatt;
    private BluetoothDevice BLE_device;
    private BluetoothGattService BLE_service;
    private BluetoothGattCharacteristic txCarac;

    public frag_salon() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_salon.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_salon newInstance(String param1, String param2) {
        frag_salon fragment = new frag_salon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //--------------
        View view = inflater.inflate(R.layout.fragment_frag_salon, container, false);
        Spinner spinner_salon = (Spinner) view.findViewById(R.id.spinner_device_salon);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.device_salon)) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);

                TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setTextColor(Color.rgb(144,198,82));

                return view;

            }
        };
        final Button connect_button= (Button) view.findViewById(R.id.connect_button_salon);

        spinner_salon.setAdapter(spinnerAdapter);
        spinner_salon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView texte_select =(TextView) parent.getChildAt(0);
                if (texte_select!=null){
                    texte_select.setTextColor(Color.rgb(144,198,82));
                }
                if (position!=0){
                    texte_select.setTextColor(Color.rgb(144,198,82));
                    connect_button.setVisibility(View.VISIBLE);
                                  }
                else {
                    connect_button.setVisibility(View.INVISIBLE);
                }
                connect_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

Log.i("STATE","connextion clic");
                        BLE_adaptater=BluetoothAdapter.getDefaultAdapter();
                        BLE_adaptater.enable();
                        BLE_scan=BLE_adaptater.getBluetoothLeScanner();

                        if (ContextCompat.checkSelfPermission(v.getContext(),
                                android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED){
                            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) v.getContext(),
                                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                            }else {
                                ActivityCompat.requestPermissions(this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        RequestLocationId);
                            }
                        }
                    }


                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
