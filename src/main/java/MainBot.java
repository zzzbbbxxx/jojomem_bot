import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class MainBot extends TelegramLongPollingBot {



    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Message message = update.getMessage();
            System.out.println(message);

            if (message.getText().equals("/jojomem")) {
                sendPhotoToChat(message, Jojomemes.getRandomMemFromBest());
            }


        }
    }


    public void sendPhotoToChat(Message message, String link)
    {
        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(message.getChatId())
                .setPhoto(link);
        try { execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace(); }
    }


    @Override
    public void onUpdatesReceived(List<Update> updates) {
        updates.forEach(this::onUpdateReceived);
    }

    @Override
    public String getBotUsername() {
        return "jojomem_bot";
        }

    @Override
    public String getBotToken() {
        return "1196113970:AAHn-wDk4d6Chh2FgJ5SvYisiTiHBb27OWI";
    }




}
