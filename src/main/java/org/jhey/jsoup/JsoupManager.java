package org.jhey.jsoup;

import org.jhey.jsoup.domain.BetterDocument;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class JsoupManager {
   private static final Logger logger = Logger.getLogger(JsoupManager.class.getName());

   private final String sessionToken;

   public JsoupManager(String sessionToken) {
      this.sessionToken = sessionToken;
   }

  private static final String NSA_HOME_PAGE_URL = "https://nsa.cps.sp.gov.br/alunos/frmmenu.aspx";
   private static final String NSA_TOKEN_COOKIE_NAME = "NSA_OnLine_SessionId";


   public Document getHomePage() {
      try {
         Document homePageDocument = createUrlConnection(NSA_HOME_PAGE_URL).get();
         BetterDocument betterHomePageDocument = new BetterDocument(homePageDocument);
         return betterHomePageDocument.getWithEncodedString(StandardCharsets.UTF_8);
      } catch (IOException e) {
        logger.severe("Something went terrible wrong");
        e.printStackTrace();
        return null;
      }
   }
   private Connection createUrlConnection(String url) {
      return Jsoup.connect(url)
              .cookie(NSA_TOKEN_COOKIE_NAME, sessionToken);
   }
}
