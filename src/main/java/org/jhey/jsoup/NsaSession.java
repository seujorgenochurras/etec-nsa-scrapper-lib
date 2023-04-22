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
   private Student student;
   private final NsaCookies sessionCookie;

   private final JsoupManager jsoupManager;

   public NsaSession(NsaCookies sessionCookies) {
      this.sessionCookie = sessionCookies;
      this.jsoupManager = new JsoupManager(sessionCookies);

      fetchStudentInfo();
   }

   private void fetchStudentInfo(){
      NsaElement studentTableElement = getStudentTableElement();
      StudentTableService studentTableService = new StudentTableService(studentTableElement);
      this.student = studentTableService.convertTableToStudent();
   }

   private NsaElement getStudentTableElement(){
      final String studentTableCssQuery = "#aspnetForm > div:nth-child(25) > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(1) > table > tbody";
      return new NsaElement(jsoupManager.getHomePage()
              .selectFirst(studentTableCssQuery));
   }

   public Student getStudent() {
      return student;
   }

   public NsaCookies getSessionCookie() {
      return sessionCookie;
   }

   private static final class StudentTableService {
      private final NsaElement studentTableElement;

      public StudentTableService(NsaElement studentTableElement) {
         this.studentTableElement = studentTableElement;
      }

      public Student convertTableToStudent() {
         studentTableElement.removeHtmlGarbage();

         String studentInfo = StudentInfoFactory
                 .of(studentTableElement.getRawText())
                 .removeInfoIdentifiers()
                 .removeUnknownChars()
                 .removeInitialSpaces()
                 .build();
         return tryMapStudentInfo(studentInfo);
      }

      private Student tryMapStudentInfo(String studentInfo) {
         String infoSplitter = " : ";
         String[] studentInfoSplitted = studentInfo.split(infoSplitter);
         try {
            return mapStudentInfoArrayToStudent(studentInfoSplitted);
         } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
            return null;
         }
      }

      private Student mapStudentInfoArrayToStudent(@NotNull String[] studentInfoArray)
              throws IllegalArgumentException {

         if (studentInfoArray.length != 8) throw new IllegalArgumentException("Student array has/hasn't too much info");
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

      private static class StudentInfoFactory {

         private String rawStudentInfoString;

         public static StudentInfoFactory of(String rawStudentInfoString){
            return new StudentInfoFactory(rawStudentInfoString);
         }

         private StudentInfoFactory(String rawStudentInfoString) {
            this.rawStudentInfoString = rawStudentInfoString;
         }

         private static final Set<String> studentInfoIdentifiers = Set.of(
                 "Identifica??o do Aluno",
                 "Nome",
                 "RA SED",
                 "Habilita??o",
                 "Matr?cula",
                 "Turma",
                 "TURMA",
                 "M?dulo/S?rie",
                 "CURSANDO",
                 "Semestre OC: Ano OC",
                 "GRUPO",
                 "S?RIE Grupo da Divis?o");

         public StudentInfoFactory removeInfoIdentifiers() {
            String studentInfoAsUtf8 = new String(rawStudentInfoString.getBytes(), StandardCharsets.UTF_8);

            AtomicReference<String> result = new AtomicReference<>(studentInfoAsUtf8);
            studentInfoIdentifiers.forEach(studentInfoIdentifier -> result.set(result.get().replace(studentInfoIdentifier, "")));

            rawStudentInfoString = result.get();
            return this;
         }
         public StudentInfoFactory removeUnknownChars(){
            rawStudentInfoString = rawStudentInfoString.replace("?", "");
            return this;
         }

         public StudentInfoFactory removeInitialSpaces(){
            rawStudentInfoString = rawStudentInfoString.replace("  ", " ");
            return this;
         }

         public String build(){
            return this.toString();
         }

         @Override
         public String toString() {
            return rawStudentInfoString;
         }
      }
   }
}

