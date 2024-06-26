package com.example.activitylifecycle.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.activitylifecycle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "fragment 2 onCreate", Toast.LENGTH_SHORT).show();

        Log.d("SecondFragment", "SecondFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "fragment 2 onCreateView", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "fragment 2 onAttach", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "fragment 2 onStart", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "fragment 2 onResume", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        //在屏幕上展示当前状态
        Toast.makeText(getActivity(), "fragment 2 onPause", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "fragment 2 onStop", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "fragment 2 onDetach", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "fragment 2 onDestroyView", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "fragment 2 onDestroy", Toast.LENGTH_SHORT).show();
        Log.d("SecondFragment", "SecondFragment onDestroy");
    }


}