import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class MainApp {


    public static void main(String[] args) {

        Jojomemes.parceRedditJojoSubreddit();

        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        MainBot bot = new MainBot();

        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }

    }

}
