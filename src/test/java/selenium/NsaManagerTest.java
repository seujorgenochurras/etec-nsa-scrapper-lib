package selenium;

import org.jhey.model.student.Student;
import org.jhey.selenium.NsaManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class NsaManagerTest {

   @Test
   void isStudentCorrect(){
      NsaManager.login(new StudentCredentials("3992", "pass", "098"), "AssemblyAIToken");
      Student student = NsaManager.getStudent();

      Assertions.assertEquals("Thiago Elias Martins", student.getName());
      Assertions.assertEquals("35269", student.getRm());
      Assertions.assertEquals("A", student.getClassDivision());
      Assertions.assertEquals("B", student.getCourseDivision());
      Assertions.assertEquals("2", student.getSchoolSeries());
      Assertions.assertEquals("M-TEC (PI) DESENVOLVIMENTO DE SISTEMAS", student.getCourseName());
   }
}
