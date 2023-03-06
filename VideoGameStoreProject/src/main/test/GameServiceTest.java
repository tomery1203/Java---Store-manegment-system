import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.hit.dao.Dao;
import com.hit.dao.VideoGameFileDao;
import com.hit.dm.GamePlatformEnum;
import com.hit.dm.VideoGame;
import com.hit.services.GameService;

class GameServiceTest {
	// private   VideoGameFileDao videoGameFileDao =   new VideoGameFileDao();


	@Test
	void saveNewGameTest() throws IOException {
		GameService gs = new GameService () ;
		Short year = 2022;
		Long id = (long) 1;
		VideoGame game = new VideoGame ("Portal",year,id,GamePlatformEnum.PC);
		GameService.addNewGame(game);
	}
	@Test
	void rentNewGameTest() throws IOException {
		GameService gs = new GameService ( );
		Short year = 2022;
		Long id = (long) 2;
		VideoGame game = new VideoGame ("The Last Of Us",year,id,GamePlatformEnum.PS4);	
		GameService.addNewGame(game);
		GameService.rentNewGame(("The Last Of Us"));
	}
	@Test
	void returnNewGameTest() throws IOException {
		GameService gs = new GameService ();
		Short year = 2022;
		Long id = (long) 3;
		VideoGame game = new VideoGame ("Call of duty Modern Warfare",year,id,GamePlatformEnum.SWITCH);	
		GameService.addNewGame(game);
		GameService.rentNewGame("Call of duty Modern Warfare");
		GameService.rentNewGame("Call of duty Modern Warfare");
	}
}
