package org.jhey.jsoup;

import org.jhey.model.student.Student;

public class NsaSession {
   private Student loggedInStudent;
   private String sessionCookie;


   public NsaSession(String sessionCookie) {
      this.sessionCookie = sessionCookie;
   }
   private void initFetchStudent(){

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
