package com.github.AlenaKhudnitskaya.WinterMoodBot.command;


import com.github.AlenaKhudnitskaya.WinterMoodBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет! Я Winter Mood Bot. " +
            "Я помогу тебе создать зимнее настроение. Я еще маленький и только учусь";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
