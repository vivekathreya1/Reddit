package com.abbaqus.vivekathreya_reddit.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.databinding.FragmentPopularBinding;
import com.abbaqus.vivekathreya_reddit.model.PopularModel;
import com.abbaqus.vivekathreya_reddit.view.adapter.PopularAdapter;
import com.abbaqus.vivekathreya_reddit.viewmodel.PopularViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private static final String TAG = "Popular fragment";
    private FragmentPopularBinding binding;
    private PopularViewModel viewModel;
    private View rootView;

    public FavoriteFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);
        viewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        binding.setViewModel(viewModel);
        setObservers();
        rootView = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.popular));
        return rootView;
    }

    private void setObservers() {
       viewModel.getFavData().observe(this, (popularModels -> {
            setAdapter(popularModels);
       }));
    }

    private void setAdapter(List<PopularModel> data) {
        binding.pbLoading.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.VISIBLE);
        PopularAdapter adapter = new PopularAdapter( getActivity(),data);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
