package org.jhey.model.html.page;

import org.jhey.captcha_breaker.stt.html.elements.captcha.Captcha;
import org.jhey.captcha_breaker.stt.selenium.captcha.CaptchaFinder;
import org.jhey.jsoup.NsaCookies;
import org.jhey.model.student.StudentCredentials;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoginPage {
   @FindBy(id = "btnEntrar")
   private WebElement loginButton;
   @FindBy(id = "txtCod")
   private WebElement etecId;
   @FindBy(id = "txtlogin")
   private WebElement userId;
   @FindBy(id = "txtSenha")
   private WebElement userPass;

   private final Captcha captcha;

   private final WebDriver webDriver;

   private final StudentCredentials studentCredentials;

   public LoginPage(WebDriver webDriver, StudentCredentials credentials) {
      this.webDriver = webDriver;
      captcha = CaptchaFinder.findCaptchaElement(webDriver);
      this.studentCredentials = credentials;
      PageFactory.initElements(webDriver, this);
   }

   private void insertStudentAccountInfo() {
      final String ETEC_ID = studentCredentials.etecId();
      final String ETEC_USER_ID = studentCredentials.rm();
      final String ETEC_PASS = studentCredentials.passWord();
      this.etecId.sendKeys(ETEC_ID);
      this.userId.sendKeys(ETEC_USER_ID);
      this.userPass.sendKeys(ETEC_PASS);
   }


   public NsaCookies loginAndGetSessionTokenCookie(String assemblyAiToken) {
      insertStudentAccountInfo();
      captcha.solveCaptcha(assemblyAiToken);
      loginButton.click();
      return getSessionTokenCookies();
   }

   private NsaCookies getSessionTokenCookies() {
      final String ARRAffinity = getCookieValue("ARRAffinity");
      final String ARRAffinitySameSite = getCookieValue("ARRAffinitySameSite");
      final String NSA_OnLine_SessionId = getCookieValue("NSA_OnLine_SessionId");

      return new NsaCookies(NSA_OnLine_SessionId, ARRAffinity, ARRAffinitySameSite);
   }
   private String getCookieValue(String cookieName){
      return  webDriver.manage()
              .getCookies()
              .stream()
              .filter(cookie -> cookie.getName().equals(cookieName))
              .map(Cookie::getValue)
              .findFirst()
              .orElseThrow(() -> new NotFoundException("Cookie " + cookieName + " was not found"));
   }
}
