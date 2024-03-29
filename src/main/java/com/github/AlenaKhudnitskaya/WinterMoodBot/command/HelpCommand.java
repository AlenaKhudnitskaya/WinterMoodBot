package com.github.AlenaKhudnitskaya.WinterMoodBot.command;

import com.github.AlenaKhudnitskaya.WinterMoodBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.AlenaKhudnitskaya.WinterMoodBot.command.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private static final String HELP_MESSAGE = String.format("✨<b>Доcтупные команды</b>✨\n\n"

            + "%s - начать работу со мной\n"
            + "%s - получить идею\n"
            + "%s - приостановить работу со мной\n\n"
            + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), RA.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
