package com.abbaqus.vivekathreya_reddit.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.databinding.SortFragmentBinding;
import com.abbaqus.vivekathreya_reddit.viewmodel.PopularViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SortTopicBottomSheetFragment extends BottomSheetDialogFragment {

    private SortFragmentBinding binding;
    private View rootView;
    private PopularViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.sort_fragment, container, false);
        rootView = binding.getRoot();
        viewModel = ViewModelProviders.of(getActivity()).get(PopularViewModel.class);
        binding.setViewModel(viewModel);
        setClickListeners();

        return rootView;
    }

    private void setClickListeners() {
        binding.RisingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.onClickRising();
                dismiss();
            }
        });

        binding.hotTv.setOnClickListener((view -> {
            viewModel.onClickHot();
            dismiss();
        }));
    }


}
