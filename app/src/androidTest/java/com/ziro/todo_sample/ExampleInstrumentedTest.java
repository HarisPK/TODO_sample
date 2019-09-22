package com.ziro.todo_sample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ziro.todo_sample.models.ToDo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private ToDoDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.toDoDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        ToDo toDo=new ToDo();
        toDo.setTitle("Haris");
        toDo.setDesc("ZIRO");
        userDao.insertNewToDo(toDo);
        List<ToDo> byName = userDao.getAllTodo();
        assertEquals(byName.get(0).getTitle(), "Haris");
    }
}
