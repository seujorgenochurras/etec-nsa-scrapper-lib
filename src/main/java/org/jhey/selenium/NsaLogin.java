package org.jhey.selenium;


import org.jhey.jsoup.NsaSession;
import org.jhey.model.html.page.LoginPage;

public class NsaLogin {
   public NsaSession login(LoginWith loginMethod){
     String studentCookie = "";
      if(loginMethod instanceof LoginWith.LoginWithCredentials credentials) {
         LoginPage loginPage = new LoginPage(credentials.getWebDriver(), credentials.toStudentCredentials());
        studentCookie = loginPage.loginAndGetSessionTokenCookie();
      }
      return new NsaSession(studentCookie);
   }

}
