package com.hit.services;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.hit.dm.VideoGame;

public class BackupAndRestore {

    public  void backup(String i_fromFilePath, String i_toFilePathBackup, long i_delay, long i_period) {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    FileInputStream fis = new FileInputStream(i_fromFilePath);
                    FileOutputStream fos = new FileOutputStream(i_toFilePathBackup);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fis.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, i_delay, i_period);
    }

    public List<VideoGame> restore(String i_fromFilePath) {
        try {
            FileInputStream fis = new FileInputStream(i_fromFilePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<VideoGame> gameList = new ArrayList<>();
            while (fis.available() > 0) {
                VideoGame game = (VideoGame) ois.readObject();
                gameList.add(game);
            }
            ois.close();
            fis.close();
            return gameList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

