package org.jhey.selenium;


import org.jhey.jsoup.NsaCookies;
import org.jhey.jsoup.NsaSession;
import org.jhey.selenium.html.page.LoginPage;


public class NsaLogin {
   private NsaLogin(){}
   public static NsaSession login(LoginWith loginMethod){
     NsaCookies studentTokenCookies = NsaCookies.emptyCookies();
      if(loginMethod instanceof LoginWith.LoginWithCredentials credentials) {
         LoginPage loginPage = new LoginPage(credentials.getWebDriver(),
                 credentials.toStudentCredentials());

         studentTokenCookies = loginPage.loginAndGetSessionTokenCookie(credentials.getAssemblyAiToken());
      }
      return new NsaSession(studentTokenCookies);
   }

}
