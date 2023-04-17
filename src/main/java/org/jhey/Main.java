package org.jhey;

import org.jhey.model.student.Student;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
   public static void main(String[] args) {
      ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
      ChromeDriver chromeDriver = new ChromeDriver(options);
      chromeDriver.get("localhost:5500/nsaPage.html");
      System.out.println("Hello world!");
   }
}