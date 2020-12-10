package com.sun.databasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.room.Update;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    WordDatabase wordDatabase;
    WordDao wordDao;
    Button buttonInsert,buttonUpdate,buttonClear,buttonDelete;
    LiveData<List<WordEntity>> liveDataWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"wordentity_db").allowMainThreadQueries().build();
        wordDatabase = WordDatabase.getDatabase(this);
        wordDao = wordDatabase.getWordDao();
        liveDataWord = wordDao.getAllWords();
        textView = findViewById(R.id.textView);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);

        liveDataWord.observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                StringBuilder text = new StringBuilder();
                for(int i=0;i<wordEntities.size();i++){
                    WordEntity wordEntity = wordEntities.get(i);
                    text.append(wordEntity.getId()).append(":").append(wordEntity.getWord()).append("=").append(wordEntity.getMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordEntity wordEntity1 = new WordEntity("hello","你好");
                WordEntity wordEntity2 = new WordEntity("world","世界");
//                wordDao.insertWords(wordEntity1,wordEntity2);
                new InsertAsyncTask(wordDao).execute(wordEntity1,wordEntity2);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordEntity wordEntity = new WordEntity("rest","休息");
                wordEntity.setId(10);
//                wordDao.deleteWord(wordEntity);
                new DeleteAsyncTask(wordDao).execute(wordEntity);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordEntity wordEntity = new WordEntity("rest","休息");
                wordEntity.setId(9);
//                wordDao.updateWords(wordEntity);
                new UpdateAsyncTask(wordDao).execute(wordEntity);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wordDao.deleteWords();
                new ClearAsyncTask(wordDao).execute();
            }
        });

    }



}