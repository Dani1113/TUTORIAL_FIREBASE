package com.example.tutorialfirebase.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tutorialfirebase.R;



    public  class HomeFragment extends Fragment {



        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_home, container, false);
         //   NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
           // NavController navController = navHostFragment.getNavController();
            //avController.navigate(R.id.fragment_empresas;
            return root;
        }

    }
