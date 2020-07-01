import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class mainApp {

    private static jojomemes jojomemes = new jojomemes();

    public static void main(String[] args)
    {

        System.out.println("begin/");
        jojomemes.parceRedditJojoSubreddit();

        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        mainBot bot = new mainBot();
        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }

    }

}
