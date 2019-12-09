package com.abbaqus.vivekathreya_reddit.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.databinding.FragmentPopularBinding;
import com.abbaqus.vivekathreya_reddit.model.PopularModel;
import com.abbaqus.vivekathreya_reddit.view.adapter.PopularAdapter;
import com.abbaqus.vivekathreya_reddit.viewmodel.PopularViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class PopularFragment extends Fragment {

    private static final String TAG = "Popular fragment";
    protected FragmentPopularBinding binding;
    protected PopularViewModel viewModel;
    protected View rootView;
    MenuItem favMenuItem;

    public PopularFragment() {
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fav_menu, menu);
        favMenuItem = menu.getItem(0);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(PopularViewModel.class);
        viewModel.getPopularList(100);
        binding.setViewModel(viewModel);
        setObservers();
        rootView = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.popular));
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.showFav:
                Navigation.findNavController(rootView).navigate(R.id.action_popularFragment_to_favFragment);
                break;

            case R.id.sort:
                Navigation.findNavController(rootView).navigate(R.id.action_popularFragment_to_sortFragment);
                break;
        }
        return true;
    }

    protected void setObservers() {

        viewModel.getData().observe(this, popularModels -> {
            if (popularModels.size() > 0) {
                setAdapter(popularModels);
                binding.pbLoading.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
            } else {
                Snackbar.make(rootView,"No data", Snackbar.LENGTH_LONG );
            }
        });

        viewModel.getFavData().observe(this, (popularModels -> {
            if ((popularModels.size() > 0)) {
                favMenuItem.setVisible(true);
            } else {
                favMenuItem.setVisible(false);
            }
        }));
    }

    protected void setAdapter(List<PopularModel> data) {
        PopularAdapter adapter = new PopularAdapter( getActivity(),data);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
