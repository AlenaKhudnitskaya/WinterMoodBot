package com.github.AlenaKhudnitskaya.WinterMoodBot.service;

import com.github.AlenaKhudnitskaya.WinterMoodBot.bot.WinterMoodBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final WinterMoodBot winterMoodBot;

    @Autowired
    public SendBotMessageServiceImpl(WinterMoodBot winterMoodBot) {
        this.winterMoodBot = winterMoodBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            winterMoodBot.execute(sendMessage);
            } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
