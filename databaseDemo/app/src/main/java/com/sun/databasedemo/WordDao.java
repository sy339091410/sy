package com.sun.databasedemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.ListIterator;

@Dao
public interface WordDao {

    @Insert
    public void insertWords(WordEntity... wordEntities);

    @Update
    public void updateWords(WordEntity... wordEntities);

    @Query("DELETE FROM WordEntity")
    public void deleteWords();

    @Delete
    public void deleteWord(WordEntity... wordEntities);

    @Query("SELECT * FROM wordentity ORDER BY id DESC")
    public LiveData<List<WordEntity>> getAllWords();
}
