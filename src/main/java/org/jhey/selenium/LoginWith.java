package org.jhey.selenium;

import org.jhey.model.student.StudentCredentials;
import org.openqa.selenium.WebDriver;

public class LoginWith {
   private LoginWith(){
      //Utility class
   }



   /**
    * @param assemblyAiToken if you don't have a session token, we have to break the captcha.
    * @param webDriver we need the webdriver to be able to
    * @see <a href="https://github.com/seujorgenochurras/captcha-breaker">Why you need the all of this stuff</a>
    * */
   public static LoginWith credentials(StudentCredentials credentials, String assemblyAiToken, WebDriver webDriver){
      return new LoginWithCredentials(credentials.rm(), credentials.passWord(), credentials.etecId(), assemblyAiToken, webDriver);
   }

   public static LoginWith sessionCookie(String sessionCookie){
      return new LoginWithSessionCookie(sessionCookie);
   }

   public static class LoginWithSessionCookie extends LoginWith{
      private final String sessionCookie;

      public LoginWithSessionCookie(String sessionCookie) {
         this.sessionCookie = sessionCookie;
      }

      public String getSessionCookie() {
         return sessionCookie;
      }
   }


   public static class LoginWithCredentials extends LoginWith{
      private final String rm;
      private final String passWord;
      private final String etecId;
      private final String assemblyAiToken;
      private final WebDriver webDriver;

      public LoginWithCredentials(String rm, String passWord, String etecId, String assemblyAiToken, WebDriver webDriver) {
         this.rm = rm;
         this.passWord = passWord;
         this.etecId = etecId;
         this.assemblyAiToken = assemblyAiToken;
         this.webDriver = webDriver;
      }

      private StudentCredentials cachedCredentials;
      public StudentCredentials toStudentCredentials(){
         if (cachedCredentials == null){
            cachedCredentials = new StudentCredentials(rm, passWord, etecId);
         }
         return cachedCredentials;
      }
      public WebDriver getWebDriver() {
         return webDriver;
      }


      public String getAssemblyAiToken() {
         return assemblyAiToken;
      }

      public String getRm() {
         return rm;
      }

      public String getPassWord() {
         return passWord;
      }

      public String getEtecId() {
         return etecId;
      }
   }
}
