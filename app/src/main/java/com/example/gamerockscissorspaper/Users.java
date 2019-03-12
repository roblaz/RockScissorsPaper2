package com.example.gamerockscissorspaper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
    private transient Context context;

    private String fileName = "mFile.txt";
    private String filePath = "MyFileStorage";

    private final List<User> list;

    private File mFile;
    private String mData = "";


    private static boolean isReadOnly() {
        String storageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(storageState);
    }
    private static boolean isAvailable() {
        String storageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(storageState);
    }




    public Users(Context context) {
        this.context = context;

        list = new ArrayList<>();

        DataInputStream in = null;
        BufferedReader br = null;
        try {
            mFile = new File(context.getExternalFilesDir ("MyFileStorage"), "mFile.txt");
            Log.e("FileStorage","MyFileStorage " + mFile);
            FileInputStream fis = new FileInputStream(mFile);
            in = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(in));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                Log.e("Load mData",strLine);
                try {
                    list.add(new User(strLine));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public User getUser(int n){
        return list.get(n);
    }

    public List<User> getUsers(){
        return list;
    }

    public void addUser(User user) {
        list.add(user);
    }

    public void save() throws Exception{
        mData = "";
        for (User user: list){
            mData = mData + user.getName() + ":" + user.getScore() + "\n";
        }
        Log.e("save","mDate " + mData);

        String baseFolder;

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseFolder = context.getExternalFilesDir("MyFileStorage").getAbsolutePath();
        }

        else {
            baseFolder = context.getFilesDir().getAbsolutePath();
        }
        Log.e("Path",baseFolder + "/" + fileName );
        File file = new File(baseFolder +"/"  + fileName);

//        FileOutputStream stream = context.openFileOutput(baseFolder +"/" + fileName, Context.MODE_PRIVATE);
        FileOutputStream stream = new FileOutputStream (file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        writer.write(mData);
        writer.newLine();
        writer.close();
    }
    public User getUser(String name){

        Log.e("getUser", "list.size:" + list.size());

        for (User user : list){

            if (user.getName().equals(name)) return user;

            Log.e("User", "user.getName()" + user.getName());
        }
        return null;
    }
}
