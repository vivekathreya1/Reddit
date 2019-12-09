package com.abbaqus.vivekathreya_reddit.viewmodel;

import android.app.Application;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abbaqus.vivekathreya_reddit.api.ApiServiceInterface;
import com.abbaqus.vivekathreya_reddit.api.RetrofitRestClient;
import com.abbaqus.vivekathreya_reddit.db.FavRepository;
import com.abbaqus.vivekathreya_reddit.model.PopularModel;
import com.abbaqus.vivekathreya_reddit.repo.popular.Child;
import com.abbaqus.vivekathreya_reddit.repo.popular.PopularRepo;
import com.abbaqus.vivekathreya_reddit.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abbaqus.vivekathreya_reddit.utils.Constants.BASE_URL;

public class PopularViewModel extends AndroidViewModel {

    private ApiServiceInterface apiServiceInterface;
    private MutableLiveData<List<PopularModel>> data = new MutableLiveData<>();
    private LiveData<List<PopularModel>> favList;
    private int responseLimit = 100;

    public PopularViewModel(@NonNull Application application) {
        super(application);
        apiServiceInterface = RetrofitRestClient.createService(ApiServiceInterface.class, application.getApplicationContext());
        FavRepository favRepository = new FavRepository(application);
        favList = favRepository.getAllFav();
    }

    public void onClickHot() {
        getList(apiServiceInterface.getPopularHotTopics(responseLimit));
    }

    public void onClickRising() {
       getList(apiServiceInterface.getPopularRisingTopics(responseLimit));
    }

    private void getList( Call<PopularRepo> popularRepoCall){
        popularRepoCall.enqueue(new Callback<PopularRepo>() {
            @Override
            public void onResponse(Call<PopularRepo> call, Response<PopularRepo> response) {
                if (response.body() != null && response.code() == 200) {
                    List<Child> responseData = response.body().getData().getChildren();
                    List<PopularModel> popularModels = new ArrayList<>();
                    for(Child d: responseData){
                        PopularModel popularModel = new PopularModel();
                        popularModel.setSubReddit(d.getData().getSubreddit());
                        popularModel.setLikes(MathUtils.formatNumbers(d.getData().getUps()));
                        popularModel.setNoOfComments(d.getData().getNumComments());
                        popularModel.setPostSince(MathUtils.elapsedTimeInHrs(d.getData().getCreatedUtc()));
                        popularModel.setName(d.getData().getName());
                        if(!d.getData().getDomain().startsWith("self")){
                            popularModel.setDomain(d.getData().getDomain());
                        }
                        popularModel.setUrl( BASE_URL + d.getData().getPermalink().substring(1, d.getData().getPermalink().length()-1) + ".json");
                        popularModel.setTitle(d.getData().getTitle());
                        if(d.getData().getPreview() != null){
                            popularModel.setImageUrl(Html.fromHtml(d.getData().getPreview().getImages().get(0).getSource().getUrl()).toString());
                        }
                        popularModels.add(popularModel);
                    }
                    data.setValue(popularModels);
                }
            }

            @Override
            public void onFailure(Call<PopularRepo> call, Throwable t) {

            }
        });
    }


    public void getPopularList(Integer limit) {
        apiServiceInterface.getPopularTopics(limit).enqueue(new Callback<PopularRepo>() {
            @Override
            public void onResponse(Call<PopularRepo> call, Response<PopularRepo> response) {
                if (response.body() != null && response.code() == 200) {
                    List<Child> responseData = response.body().getData().getChildren();
                    List<PopularModel> popularModels = new ArrayList<>();
                    for(Child d: responseData){
                        PopularModel popularModel = new PopularModel();
                        popularModel.setSubReddit(d.getData().getSubreddit());
                        popularModel.setLikes(MathUtils.formatNumbers(d.getData().getUps()));
                        popularModel.setNoOfComments(d.getData().getNumComments());
                        popularModel.setPostSince(MathUtils.elapsedTimeInHrs(d.getData().getCreatedUtc()));
                        popularModel.setName(d.getData().getName());
                        if(!d.getData().getDomain().startsWith("self")){
                            popularModel.setDomain(d.getData().getDomain());
                        }
                        popularModel.setUrl( BASE_URL + d.getData().getPermalink().substring(1, d.getData().getPermalink().length()-1) + ".json");
                        popularModel.setTitle(d.getData().getTitle());
                        if(d.getData().getPreview() != null){
                            popularModel.setImageUrl(Html.fromHtml(d.getData().getPreview().getImages().get(0).getSource().getUrl()).toString());
                        }
                        popularModels.add(popularModel);
                    }
                    data.setValue(popularModels);
                }
            }

            @Override
            public void onFailure(Call<PopularRepo> call, Throwable t) {
            }
        });

    }

    public MutableLiveData<List<PopularModel>> getData() {
        return data;
    }

    public LiveData<List<PopularModel>> getFavData() {
        return favList;
    }



}
