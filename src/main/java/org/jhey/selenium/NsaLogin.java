package org.jhey.selenium;


import org.jhey.jsoup.NsaSession;
import org.jhey.model.html.page.LoginPage;

public class NsaLogin {
   private NsaLogin(){}
   public static NsaSession login(LoginWith loginMethod){
     String studentTokenCookie = "";
      if(loginMethod instanceof LoginWith.LoginWithCredentials credentials) {
         LoginPage loginPage = new LoginPage(credentials.getWebDriver(),
                 credentials.toStudentCredentials());

        studentTokenCookie = loginPage.loginAndGetSessionTokenCookie(credentials.getAssemblyAiToken());
      }
      return new NsaSession(studentTokenCookie);
   }

}
