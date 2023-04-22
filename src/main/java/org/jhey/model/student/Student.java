package org.jhey.model.student;

public class Student {
   private String classDivision;
   private String courseDivision;
   private String name;
   private String enrolmentRecord;
   private String courseName;
   private String schoolGrade;
   private String etecId;
   
   private String studentRegistryId;

   private String yearThatJoinedSchool;

   public String getEtecId() {
      return etecId;
   }

   public Student setEtecId(String etecId) {
      this.etecId = etecId;
      return this;
   }

   public String getClassDivision() {
      return classDivision;
   }

   public Student setClassDivision(String classDivision) {
      this.classDivision = classDivision;
      return this;
   }

   public String getCourseDivision() {
      return courseDivision;
   }

   public Student setCourseDivision(String courseDivision) {
      this.courseDivision = courseDivision;
      return this;
   }

   public String getStudentRegistryId() {
      return studentRegistryId;
   }

   public Student setStudentRegistryId(String studentRegistryId) {
      this.studentRegistryId = studentRegistryId;
      return this;
   }

   public String getYearThatJoinedSchool() {
      return yearThatJoinedSchool;
   }

   public Student setYearThatJoinedSchool(String yearThatJoinedSchool) {
      this.yearThatJoinedSchool = yearThatJoinedSchool;
      return this;
   }

   public String getName() {
      return name;
   }

   public Student setName(String name) {
      this.name = name;
      return this;
   }

   public String getEnrolmentRecord() {
      return enrolmentRecord;
   }

   public Student setEnrolmentRecord(String enrolmentRecord) {
      this.enrolmentRecord = enrolmentRecord;
      return this;
   }

   public String getCourseName() {
      return courseName;
   }

   public Student setCourseName(String courseName) {
      this.courseName = courseName;
      return this;
   }

   public String getSchoolGrade() {
      return schoolGrade;
   }

   public Student setSchoolGrade(String schoolGrade) {
      this.schoolGrade = schoolGrade;
      return this;
   }

   @Override
   public String toString() {
      return "Student{\n" +
              " \tclassDivision='" + classDivision + '\'' +
              ",\n \t courseDivision='" + courseDivision + '\'' +
              ",\n \t name='" + name + '\'' +
              ",\n \t enrolmentRecord='" + enrolmentRecord + '\'' +
              ",\n \t courseName='" + courseName + '\'' +
              ",\n \t schoolGrade='" + schoolGrade + '\'' +
              ",\n \t etecId='" + etecId + '\'' +
              ",\n \t studentRegistryId='" + studentRegistryId + '\'' +
              ",\n \t yearThatJoinedSchool='" + yearThatJoinedSchool + '\'' +
              '}';
   }
}
