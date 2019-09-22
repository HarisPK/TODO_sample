package com.ziro.todo_sample;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ziro.todo_sample.models.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert
    void insertNewToDo(ToDo toDo);

    @Query("select * from ToDo")
    List<ToDo> getAllTodo();

    @Query("delete from ToDo where isCheck=:isCheck")
    void getCheckedToDo(boolean isCheck);

    @Query("select * from ToDo where id=:id")
    ToDo getToDo(int id);

    @Query("update ToDo SET `title`=:title,`desc`=:description,`date`=:dateTime where id=:id")
    void updateTodo(int id, String title, String description, String dateTime);

    @Query("delete from ToDo where id=:id")
    void deleteToDo(int id);

    @Query("update ToDo SET `isCheck`=:isChecked where id=:id")
    void updateIsChecked(int id, boolean isChecked);
}
