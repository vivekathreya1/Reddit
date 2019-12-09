package com.abbaqus.vivekathreya_reddit.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.abbaqus.vivekathreya_reddit.R;
import com.abbaqus.vivekathreya_reddit.databinding.PopularRowBinding;
import com.abbaqus.vivekathreya_reddit.db.FavRepository;
import com.abbaqus.vivekathreya_reddit.model.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private Context context;
    private List<PopularModel> data;
    private FavRepository favRepository;

    public PopularAdapter(Context context, List<PopularModel> data) {
        this.context = context;
        this.data = data;
        favRepository = new FavRepository(context);
    }


    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PopularRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.popular_row, parent, false);
        return new PopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        PopularModel respone = data.get(position);
        holder.bind(respone);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{

        PopularRowBinding binding;

        PopularViewHolder(PopularRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setViewHolder(this);
        }


         void bind(Object obj) {
            binding.setVariable(com.abbaqus.vivekathreya_reddit.BR.obj, obj);
            binding.executePendingBindings();
            int id = favRepository.checkIfTitleExistsInOtherRow(((PopularModel)obj).getTitle());
            if(id<=0){
                binding.favIv.setImageDrawable(context.getDrawable(R.drawable.ic_star_border_black_24dp));
            }else{
                binding.favIv.setImageDrawable(context.getDrawable(R.drawable.ic_star_gold_24dp));
            }
        }

        public void onClickAddFav(PopularModel model) {
            int existId = favRepository.checkIfTitleExistsInOtherRow(model.getTitle());
            if(existId <=0){
                favRepository.insert(model);
                binding.favIv.setImageDrawable(context.getDrawable(R.drawable.ic_star_gold_24dp));
            }else{
                favRepository.deleteFav(existId);
                binding.favIv.setImageDrawable(context.getDrawable(R.drawable.ic_star_border_black_24dp));
            }

        }

        public void onClickShare(PopularModel model) {
            Intent shareUrl =new Intent(android.content.Intent.ACTION_SEND);
            shareUrl.setType("text/plain");
            shareUrl.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            shareUrl.putExtra(android.content.Intent.EXTRA_TEXT, model.getUrl());
            context.startActivity(Intent.createChooser(shareUrl, "Share"));
        }

        public void onClickGoToComment(PopularModel model) {
            Bundle bundle = new Bundle();
            bundle.putString(context.getString(R.string.comments_Url),model.getUrl());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_popularFragment_to_commentFragment, bundle);
        }
    }



}
