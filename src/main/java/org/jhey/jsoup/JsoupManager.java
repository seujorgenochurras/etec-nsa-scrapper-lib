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

   private final NsaCookies sessionCookies;

   public JsoupManager(NsaCookies sessionCookies) {
      this.sessionCookies = sessionCookies;
   }

   private static final String NSA_HOME_PAGE_URL = "https://nsa.cps.sp.gov.br/alunos/frmmenu.aspx";

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
              .cookies(sessionCookies);
   }
}
