package org.jhey.jsoup;

import io.netty.channel.Channel;
import org.jhey.jsoup.domain.NsaElement;
import org.jhey.model.student.Student;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

public class NsaSession {
   private Student loggedInStudent;
   private String sessionCookie;

   private final JsoupManager jsoupManager;

   public NsaSession(String sessionCookie) {
      this.sessionCookie = sessionCookie;
      this.jsoupManager = new JsoupManager(sessionCookie);

      initFetchStudentInfo();
   }
   private void initFetchStudentInfo(){
      final String studentTableCssQuery = "#aspnetForm > div:nth-child(25) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(1) > table > tbody";

      NsaElement studentTableElement = new NsaElement(jsoupManager.getHomePage()
              .selectFirst(studentTableCssQuery));
      studentTableElement.removeHtmlGarbage();
      System.out.println(studentTableElement.getRawText());
   }
   private Elements filterTagFromHtmlDocument(String tag, Document htmlDocument){
      return htmlDocument.getElementsByTag(tag);
   }

   public Student getLoggedInStudent() {
      return loggedInStudent;
   }

   public NsaSession setLoggedInStudent(Student loggedInStudent) {
      this.loggedInStudent = loggedInStudent;
      return this;
   }

   public String getSessionCookie() {
      return sessionCookie;
   }

   public void setSessionCookie(String sessionCookie) {
      this.sessionCookie = sessionCookie;
   }
}
