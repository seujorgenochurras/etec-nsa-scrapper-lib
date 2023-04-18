package org.jhey.model.student;

public class Student {
   private String classDivision;
   private String courseDivision;
   private String name;
   private String rm;
   private String courseName;
   private String schoolSeries;
   private String etecId;

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

   public String getName() {
      return name;
   }

   public Student setName(String name) {
      this.name = name;
      return this;
   }

   public String getRm() {
      return rm;
   }

   public Student setRm(String rm) {
      this.rm = rm;
      return this;
   }

   public String getCourseName() {
      return courseName;
   }

   public Student setCourseName(String courseName) {
      this.courseName = courseName;
      return this;
   }

   public String getSchoolSeries() {
      return schoolSeries;
   }

   public Student setSchoolSeries(String schoolSeries) {
      this.schoolSeries = schoolSeries;
      return this;
   }
}
