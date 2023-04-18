package selenium;

import org.jhey.model.student.Student;
import org.jhey.selenium.LoginWith;
import org.jhey.selenium.NsaLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class NsaLoginTest {

   @Test
   void isManagerWorkingWithStudentCredentials(){
      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
      ChromeDriver chromeDriver = new ChromeDriver(options);
      chromeDriver.get("localhost:5500/nsaPage.html");

      NsaLogin nsaLogin = new NsaLogin();
      nsaLogin.login(LoginWith.credentials("6969", "pass", "420", "AssemblyAiToken", chromeDriver ));
      Student student = nsaLogin.getStudent();

      Assertions.assertEquals("Thiago Elias Martins", student.getName());
      Assertions.assertEquals("35169", student.getRm());
      Assertions.assertEquals("A", student.getClassDivision());
      Assertions.assertEquals("B", student.getCourseDivision());
      Assertions.assertEquals("2", student.getSchoolSeries());
      Assertions.assertEquals("M-TEC (PI) DESENVOLVIMENTO DE SISTEMAS", student.getCourseName());
   }
   @Test
   void isManagerWorkingWithCookies(){
      NsaLogin nsaLogin = new NsaLogin();
      nsaLogin.login(LoginWith.sessionCookie("SESSION COOKIE HERE"));
      Student student = NsaLogin.getStudent();

      Assertions.assertEquals("Thiago Elias Martins", student.getName());
      Assertions.assertEquals("35169", student.getRm());
      Assertions.assertEquals("A", student.getClassDivision());
      Assertions.assertEquals("B", student.getCourseDivision());
      Assertions.assertEquals("2", student.getSchoolSeries());
      Assertions.assertEquals("M-TEC (PI) DESENVOLVIMENTO DE SISTEMAS", student.getCourseName());
   }
}
