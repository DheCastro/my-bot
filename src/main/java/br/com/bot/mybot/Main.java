package br.com.bot.mybot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.bot.mybot.MyBot;

/**
 * Classe de inicialização do bot
 * @author Dhellano
 *
 */
public class Main {

	public static void main(String[] args) {
		
		ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
