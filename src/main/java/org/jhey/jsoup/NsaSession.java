package org.jhey.jsoup;

import org.jetbrains.annotations.NotNull;
import org.jhey.jsoup.domain.NsaElement;
import org.jhey.model.student.Student;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class NsaSession {
   private static final Logger logger = Logger.getLogger(NsaSession.class.getName());
   private Student loggedInStudent;
   private String sessionCookie;

   private final JsoupManager jsoupManager;

   public NsaSession(String sessionCookie) {
      this.sessionCookie = sessionCookie;
      this.jsoupManager = new JsoupManager(sessionCookie);

      initFetchStudentInfo();
   }

   private static final Set<String> studentInfoIdentifiers = Set.of(
           "Identifica??o do Aluno",
           "Nome",
           "RA SED",
           "Habilita??o",
           "Matr?cula",
           "Turma",
           "M?dulo/S?rie",
           "CURSANDO",
           "Semestre OC: Ano OC",
           "GRUPO",
           "S?RIE Grupo da Divis?o");
   private void initFetchStudentInfo(){
      NsaElement studentTableElement = getStudentTableElement();
      studentTableElement.removeHtmlGarbage();
      String rawStudentInfo = removeIdentifiersFromStudentInfo(studentTableElement.getRawText()).replace("?", "");
      String[] rawStudentInfoAsArray = rawStudentInfo.split(" : ");

      System.out.println(tryMapStudentWithStringArray(rawStudentInfoAsArray));
   }
   private Student tryMapStudentWithStringArray(String[] studentInfoArray){
      try{
         return mapStudentInfoArrayToStudent(studentInfoArray);
      }catch (IllegalArgumentException e){
         logger.severe(e.getMessage());
         e.printStackTrace();
         return null;
      }
   }
   private Student mapStudentInfoArrayToStudent(@NotNull String[] studentInfoArray) throws IllegalArgumentException{
      if(studentInfoArray.length != 8) throw new IllegalArgumentException("Student array has/hasn't too much info");
      final int enrolmentRecordIndex = 0;
      final int nameIndex = 1;
      final int studentRegistryIdIndex = 2;
      final int courseNameIndex = 3;
      final int classDivisionIndex = 4;
      final int yearThatJoinedSchoolIndex = 5;
      final int schoolGradeIndex = 6;
      final int courseDivisionIndex = 7;
      final String etecId = "098"; //this app is only supposed to be used on my school :p
      return new Student()
              .setEnrolmentRecord(studentInfoArray[enrolmentRecordIndex])
              .setSchoolGrade(studentInfoArray[schoolGradeIndex])
              .setName(studentInfoArray[nameIndex])
              .setCourseName(studentInfoArray[courseNameIndex])
              .setClassDivision(studentInfoArray[classDivisionIndex])
              .setEtecId(etecId)
              .setCourseDivision(studentInfoArray[courseDivisionIndex])
              .setYearThatJoinedSchool(studentInfoArray[yearThatJoinedSchoolIndex])
              .setStudentRegistryId(studentInfoArray[studentRegistryIdIndex]);
   }
   private String removeIdentifiersFromStudentInfo(@NotNull String studentInfo){
      String studentInfoAsUtf8 = new String(studentInfo.getBytes() , StandardCharsets.UTF_8);

      AtomicReference<String> result = new AtomicReference<>(studentInfoAsUtf8);
      studentInfoIdentifiers.forEach(studentInfoIdentifier -> result.set(result.get().replace(studentInfoIdentifier, "")));
      return result.get();
   }

   private NsaElement getStudentTableElement(){
      final String studentTableCssQuery = "#aspnetForm > div:nth-child(25) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(1) > table > tbody";

      return new NsaElement(jsoupManager.getHomePage()
              .selectFirst(studentTableCssQuery));
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
