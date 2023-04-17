package org.jhey.model.student;

public class Student {
  private String classDivision;
  private String courseDivision;
  private String name;
  private String rm;
  private String classCourse;
  private String schoolGrade;

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

   public String getClassCourse() {
      return classCourse;
   }

   public Student setClassCourse(String classCourse) {
      this.classCourse = classCourse;
      return this;
   }

   public String getSchoolGrade() {
      return schoolGrade;
   }

   public Student setSchoolGrade(String schoolGrade) {
      this.schoolGrade = schoolGrade;
      return this;
   }
}
