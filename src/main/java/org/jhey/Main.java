package org.jhey;

import io.github.cdimascio.dotenv.Dotenv;
import org.jhey.jsoup.NsaSession;
import org.jhey.student.StudentCredentials;
import org.jhey.selenium.LoginWith;
import org.jhey.selenium.NsaLogin;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
   public static void main(String[] args) {
      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
      ChromeDriver chromeDriver = new ChromeDriver(options);
      chromeDriver.get("https://nsa.cps.sp.gov.br/");
      Dotenv dotenv = Dotenv.load();

      String rm = dotenv.get("RM");
      String password = dotenv.get("PASSWORD");
      String etecId = dotenv.get("ETEC_ID");
      String assemblyAiToken = dotenv.get("ASSEMBLYAI_TOKEN");

      StudentCredentials credentials = new StudentCredentials(rm, password, etecId);

     NsaSession session =
             NsaLogin.login(LoginWith.credentials(credentials, assemblyAiToken, chromeDriver));
   }
}