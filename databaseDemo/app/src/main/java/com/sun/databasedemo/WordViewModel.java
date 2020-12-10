package com.sun.databasedemo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    private LiveData<List<WordEntity>> allWordsLive;

    public LiveData<List<WordEntity>> getAllWordsLive() {
        return allWordsLive;
    }

    public void setAllWordsLive(LiveData<List<WordEntity>> allWordsLive) {
        this.allWordsLive = allWordsLive;
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
        WordDatabase wordDatabase = WordDatabase.getDatabase(application);
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWords();
    }


    public void insertWords(WordEntity...WordEntyties){
        new InsertAsyncTask(wordDao).execute(WordEntyties);
    }


    //继承aysnctask将对数据库的操作放入子线程
    static class InsertAsyncTask extends AsyncTask<WordEntity,Void,Void> {
        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            wordDao.insertWords(wordEntities);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<WordEntity,Void,Void>{
        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            wordDao.updateWords(wordEntities);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;

        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            wordDao.deleteWords();
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<WordEntity,Void,Void>{
        private WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            wordDao.deleteWord(wordEntities);
            return null;
        }
    }

}
