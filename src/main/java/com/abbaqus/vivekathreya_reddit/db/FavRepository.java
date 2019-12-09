package com.abbaqus.vivekathreya_reddit.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.abbaqus.vivekathreya_reddit.model.PopularModel;

import java.util.List;

public class FavRepository {
    private FavDao favDao;
    private LiveData<List<PopularModel>> favList;


    public FavRepository(Context context) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        favDao = db.favDao();
        favList = favDao.getFavList();

    }

    public LiveData<List<PopularModel>> getAllFav() {
        return favDao.getFavList();
    }

    public Long insert(PopularModel popularModel) {
        InsertAsyncTask task = new InsertAsyncTask(favDao);
        Long id = null;
        try {
            id = task.execute(popularModel).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private static class InsertAsyncTask extends AsyncTask<PopularModel, Void, Long> {
        private FavDao mAsyncTaskDao;

        InsertAsyncTask(FavDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final PopularModel... params) {
            return mAsyncTaskDao.insert(params[0]);
        }
    }

    public int checkIfTitleExistsInOtherRow(String name){
        CheckIfTitleExistsInOtherRowAsyncTask task = new CheckIfTitleExistsInOtherRowAsyncTask(favDao);
        int id = -1;
        try {
            id = task.execute(name).get();
        } catch (Exception e) {
        }
        return id;

    }


    private static class CheckIfTitleExistsInOtherRowAsyncTask extends AsyncTask<String, Void, Integer> {
        private FavDao mAsyncTaskDao;
        CheckIfTitleExistsInOtherRowAsyncTask(FavDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(final String... params) {
            return mAsyncTaskDao.ifTitleExists(params[0]);
        }
    }


    public int deleteFav(int inputId){
        DeleteFavAsyncTask task = new DeleteFavAsyncTask(favDao);
        int rows = -1;
        try {
            rows = task.execute(inputId).get();
        } catch (Exception e) {
        }
        return rows;
    }

    private static class DeleteFavAsyncTask extends AsyncTask<Integer, Void, Integer> {
        private FavDao mAsyncTaskDao;

        DeleteFavAsyncTask(FavDao favDao) {
            mAsyncTaskDao = favDao;
        }

        @Override
        protected Integer doInBackground(Integer... favs) {
            return mAsyncTaskDao.deleteFav(favs[0]);
        }
    }

}
