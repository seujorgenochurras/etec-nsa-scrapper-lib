package selenium;

import io.github.cdimascio.dotenv.Dotenv;
import org.jhey.jsoup.NsaSession;
import org.jhey.model.student.Student;
import org.jhey.model.student.StudentCredentials;
import org.jhey.selenium.LoginWith;
import org.jhey.selenium.NsaLogin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class NsaLoginTest {

   @Test
   void isNsaLoginLoggingIn(){
      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
      ChromeDriver chromeDriver = new ChromeDriver(options);
      chromeDriver.get("https://nsa.cps.sp.gov.br/");

      Dotenv dotenv = Dotenv.load();

      String rm = dotenv.get("RM");
      String password = dotenv.get("PASSWORD");
      String etecId = dotenv.get("ETEC_ID");
      String assemblyAiToken = dotenv.get("ASSEMBLYAI_TOKEN");

      StudentCredentials credentials = new StudentCredentials(rm, password, etecId);

      NsaSession nsaSession = NsaLogin.login(LoginWith.credentials(credentials, assemblyAiToken, chromeDriver));

      Assertions.assertNotNull(nsaSession.getSessionCookie());
      chromeDriver.close();
   }

   @Test
   void isManagerWorkingWithStudentCredentials(){
//      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
//      ChromeDriver chromeDriver = new ChromeDriver(options);
//      chromeDriver.get("localhost:5500/nsaPage.html");
//
//      StudentCredentials credentials = new StudentCredentials("6969", "pass", "420");
//      NsaSession session = NsaLogin.login(LoginWith.credentials(credentials,"AssemblyAiToken", chromeDriver ));
      NsaSession session = new NsaSession("cookie");

      Student student = session.getStudent();

      Assertions.assertEquals("Thiago Elias Martins", student.getName());
      Assertions.assertEquals("35269", student.getEnrolmentRecord());
      Assertions.assertEquals("A", student.getClassDivision());
      Assertions.assertEquals("B", student.getCourseDivision());
      Assertions.assertEquals("2", student.getSchoolGrade());
      Assertions.assertEquals("M-TEC (PI) DESENVOLVIMENTO DE SISTEMAS", student.getCourseName());
   }
//   @Test
//   void isManagerWorkingWithCookies(){
//      NsaLogin nsaLogin = new NsaLogin();
//      nsaLogin.login(LoginWith.sessionCookie("SESSION COOKIE HERE"));
//
//      Assertions.assertEquals("Thiago Elias Martins", student.getName());
//      Assertions.assertEquals("35169", student.getRm());
//      Assertions.assertEquals("A", student.getClassDivision());
//      Assertions.assertEquals("B", student.getCourseDivision());
//      Assertions.assertEquals("2", student.getSchoolSeries());
//      Assertions.assertEquals("M-TEC (PI) DESENVOLVIMENTO DE SISTEMAS", student.getCourseName());
//   }
}
