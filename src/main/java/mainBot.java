import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class mainBot extends TelegramLongPollingBot {



    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Message message = update.getMessage();
            System.out.println(message);

//            if (!message.getNewChatMembers().isEmpty()) {
//                replyMessageToChat(message, "...мем или бан?!");
//            }

//            if (message.getLeftChatMember() != null) {
//                replyMessageToChat(message, "https://www.youtube.com/watch?v=gTfM4SmYZ3U");
//            }

            if (message.getText().equals("/jojomem")) {
                jojomemes mem = new jojomemes();
                sendPhotoToChat(message, mem.getRandomMemLink());
            }

            if (message.getText().equals("text")) {
                _allshifts(message);
            }


        }
    }

    public void sendMessageToChat(Message message, String textMessage)
    {

        System.out.println(textMessage);
        SendMessage sendMessage = new SendMessage()
            .enableMarkdown(true)
            .setChatId(message.getChatId())
                .setText(textMessage);

        try { execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace(); }
    }

//    public void replyMessageToChat(Message message, String textMessage)
//    {
//        System.out.println(textMessage);
//        SendMessage sendMessage = new SendMessage()
//                .setChatId(message.getChatId())
//                .setReplyToMessageId(message.getMessageId())
//                .setText(textMessage);
//
//        try { execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace(); }
//    }


    public void _allshifts(Message message)
    {
        SendMessage sm = new SendMessage();
        sm.setChatId(message.getChatId());
        sm.setParseMode(ParseMode.HTML);

        String finalMessage = new String("<b>bold</b>, <strong>bold</strong>\n");
        finalMessage = finalMessage.concat("<i>italic</i>, <em>italic</em>\n");
        finalMessage = finalMessage.concat("<u>underline</u>, <ins>underline</ins>\n");
        finalMessage = finalMessage.concat("<s>strikethrough</s>, <strike>strikethrough</strike>, <del>strikethrough</del>\n");
        finalMessage = finalMessage.concat("<b>bold <i>italic bold <s>italic bold strikethrough</s> <u>underline italic bold</u></i> bold</b>\n");
        finalMessage = finalMessage.concat("<a href=\"http://www.example.com/\">inline URL</a>\n");
        finalMessage = finalMessage.concat("<a href=\"tg://user?id=123456789\">inline mention of a user</a>\n");
        finalMessage = finalMessage.concat("<code>inline fixed-width code</code>\n");
        finalMessage = finalMessage.concat("<pre>pre-formatted fixed-width code block</pre>\n");
        finalMessage = finalMessage.concat("<pre><code class=\"language-python\">pre-formatted fixed-width code block written in the Python programming language</code></pre>\n");
        sm.setText(finalMessage);

        try { execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace(); }
    }


    public void sendPhotoToChat(Message message, String link)
    {
        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(message.getChatId())
                .setReplyToMessageId(message.getMessageId())
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
        return "vishnya_welcomer_bot";
    }

    @Override
    public String getBotToken() {
        return "899327279:AAHoZJyBFobBKp75xMijszdS_iTFpBW9kqs";
    }




}
