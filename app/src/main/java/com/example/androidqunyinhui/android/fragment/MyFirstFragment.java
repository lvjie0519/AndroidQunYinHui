package com.example.androidqunyinhui.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidqunyinhui.R;


public class MyFirstFragment extends Fragment {

    public MyFirstFragment() {
        // Required empty public constructor
    }

    public static MyFirstFragment newInstance() {
        MyFirstFragment fragment = new MyFirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my_first, container, false);
    }
}
