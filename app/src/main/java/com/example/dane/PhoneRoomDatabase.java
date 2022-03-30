package com.example.dane;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PhoneRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PhoneRoomDatabase.class, "Phone")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static  final int NUMNER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMNER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {
                PhoneDao dao = INSTANCE.phoneDao();
                Phone firstPhone = new Phone(null, "Samsung", "A32", "11", "www.samsung.com");
                dao.insert(firstPhone);
            });
        }
    };
}
