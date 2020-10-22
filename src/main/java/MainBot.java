import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MainBot extends TelegramLongPollingBot {

    Update update;
    long chat_id;
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {

        update.getUpdateId();
        chat_id = update.getMessage().getChatId();

        if (update.hasMessage()) {

            Message message = update.getMessage();


            if (message.getText().equals("/start")) {
                System.out.println(message);
                deleteMessage(message.getMessageId());
                sendPhotoToChat(message, Jojomemes.getRandomMemFromBest());
            }


            if (message.getText().equals("/jojomem")) {
                System.out.println(message);
                deleteMessage(message.getMessageId());
                sendPhotoToChat(message, Jojomemes.getRandomMemFromBest());
            }

            if (message.getText().equals("getFromBest")) {
                System.out.println(message);
                deleteMessage(message.getMessageId());
                sendPhotoToChat(message, Jojomemes.getRandomMemFromBest());
            }

            if (message.getText().equals("getFromNew")) {
                System.out.println(message);
                deleteMessage(message.getMessageId());
                sendPhotoToChat(message, Jojomemes.getNewMem());
            }

        }
    }


    public void deleteMessage(Integer messageId) {

        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(messageId);
        deleteMessage.setChatId(chat_id);

        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setBoard() {

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        keyboard.clear();
        keyboardRow.clear();

        keyboardRow.add("getFromBest");
        keyboardRow.add("getFromNew");

        keyboard.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

    }


    public void sendPhotoToChat(Message message, String link)
    {

        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(message.getChatId())
                .setPhoto(link);

        setBoard();
        sendPhoto.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
