package com.example.activitylifecycle.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.activitylifecycle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstFragment(String mParam1) {
        this.mParam1 = mParam1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Toast.makeText(getActivity(), "fragment 1 onCreate", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "fragment 1 onCreateView", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);


    }


    @Override
    public void onPause() {
        super.onPause();
        //在屏幕上展示当前状态
        Toast.makeText(getActivity(), "fragment 1 onPause", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onPause");
    }
    public void showLongToast(int resId){
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), resId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP,0, 0);
        toast.show();
    }

    private void showToast(Context context,String text) {
        Toast toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    @Override
    public void onStart() {
        super.onStart();
        showLongToast(R.string.app_name);

        Log.d("FirstFragment", "FirstFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "fragment 1 onResume", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onResume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "fragment 1 onAttach", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "fragment 1 onDetach", Toast.LENGTH_SHORT).show();

        Log.d("FirstFragment", "FirstFragment onDetach");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(), "fragment 1 onStop", Toast.LENGTH_SHORT).show();
        Log.d("FirstFragment", "FirstFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "fragment 1 onDestroyView", Toast.LENGTH_SHORT).show();
        Log.d("FirstFragment", "FirstFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "fragment 1 onDestroy", Toast.LENGTH_SHORT).show();
        Log.d("FirstFragment", "FirstFragment onDestroy");
    }
}