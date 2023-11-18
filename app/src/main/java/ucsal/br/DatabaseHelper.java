package ucsal.br;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aqui você cria suas tabelas usando SQL
        String CREATE_TABLE = "CREATE TABLE my_table (_id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aqui você pode atualizar o esquema do banco de dados se a versão mudar
        db.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(db);
    }

    public List<MyTableEntry> getAllData() {
        List<MyTableEntry> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = new String[]{"_id", "name", "age"};
        try (Cursor cursor = db.query("my_table", columns, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                    dataList.add(new MyTableEntry(id, name, age));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return dataList;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("my_table", "_id = ?", new String[]{id});
    }

    public boolean insertData(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);

        long result = db.insert("my_table", null, contentValues);
        db.close();

        // Se result é -1, ocorreu um erro
        return result != -1;
    }
    public boolean updateData(String id, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);

        int result = db.update("my_table", contentValues, "_id = ?", new String[] { id });
        db.close();

        // If result is > 0, the update was successful
        return result > 0;
    }

    public static class MyTableEntry {
        private int id;
        private String name;
        private int age;

        public MyTableEntry(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @NonNull
        @Override
        public String toString() {
            // Ensure that this never returns null
            return "ID: " + id + ", Name: " + (name != null ? name : "N/A") + ", Age: " + age;
        }
    }
}
