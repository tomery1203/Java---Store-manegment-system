package com.hit.service;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.hit.services.*;
import org.junit.jupiter.api.Test;

import com.hit.dm.GamePlatformEnum;
import com.hit.dm.VideoGame;

public class BackupAndRestoreTest {
	 String fromFilePath = "AvailbleGames.txt";
     String toFilePath = "BackUp.txt";
     @Test
     public void testBackUP() {
         Short year = 2020;
         Long id = 1L;
         VideoGame game = new VideoGame("The Last of Us Part II", year, id, GamePlatformEnum.PS4);
         try (ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(fromFilePath))) {
             objectInputStream.writeObject(game);
         } catch (Exception e) {
             e.printStackTrace();
         }
         BackupAndRestore backupManager = new BackupAndRestore();
         backupManager.backup(fromFilePath, toFilePath, 0, 1000);
     }
	  @Test 
	  public void testRestore() {
		  BackupAndRestore backupManager = new BackupAndRestore();
		List<VideoGame> game =  backupManager.restore(toFilePath);
	  }
	 
}
