package br.com.bot.mybot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Classe de gerenciamento do bot criado a partir do @BotFather do telegram
 * @author Dhellano
 *
 */
public class MyBot extends TelegramLongPollingBot {

	//retorne o username do seu bot
	public String getBotUsername() {
		return "username aqui";
	}
	
	//retorne o token do seu bot informado pelo botfather
	@Override
	public String getBotToken() {
		return "seu token aqui";
	}

	//Método responsável por receber as novas mensagens
	public void onUpdateReceived(Update update) {
		
		GetFile getFileRequest = new GetFile();
		 
		//Verifica se a mensagem recebida é uma foto
		if(update.getMessage().getPhoto()!=null){
			
	        getFileRequest.setFileId(update.getMessage().getPhoto().get(0).getFileId());
	        criarArquivo(getFileRequest);  
	     
	     //Verifica se a mensagem recebida é um documento
		}else if(update.getMessage().getDocument()!=null){
			
	        getFileRequest.setFileId(update.getMessage().getDocument().getFileId());
	        criarArquivo(getFileRequest); 
	        
		}
	}

	/**
	 * Método responsável por receber o arquivo do telegram, fazer o download e salvar dentro do projeto
	 * @param getFileRequest
	 */
	public void criarArquivo(GetFile getFileRequest) {
		
		org.telegram.telegrambots.api.objects.File fileTelegram;
		File file;
		
		try {
			
			fileTelegram = execute(getFileRequest);
			file = new File(fileTelegram.getFilePath());
	        URL url = new URL("https://api.telegram.org/file/bot"+getBotToken()+"/"+fileTelegram.getFilePath());
	        FileUtils.copyURLToFile(url, file);
		
		} catch (TelegramApiException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}

