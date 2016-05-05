
package home;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class DKTNew extends PerfDB {
	public WebDriver driver;
	public String expStr1 = "Customer Charter";
	public String expStr2 = "Do it online";
	public String expStr3 = "Practice driver knowledge test";
	public String expStr4 = "Driver Knowledge Test (DKT)";
	public String expStr5 = "          Welcome to RTA's Online Demonstration Driver Knowledge Test";
	
	public StopWatch RMSHome = new StopWatch();
	public StopWatch DriverTest = new StopWatch();
	public StopWatch PracticeDKT = new StopWatch();
	public StopWatch LaunchDKT = new StopWatch();
	
	public PerfDB myPerfDB = new PerfDB();
	
	@Test
	public void DKT() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	//public static void main() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException{
		
		System.out.println("First check");
		
		WebDriver driver;
		String expStr1 = "Customer Charter";
		String expStr2 = "Do it online";
		String expStr3 = "Practice driver knowledge test";
		 String expStr4 = "Driver Knowledge Test (DKT)";
		 String expStr5 = "          Welcome to RTA's Online Demonstration Driver Knowledge Test";
		
		StopWatch RMSHome = new StopWatch();
		StopWatch DriverTest = new StopWatch();
		StopWatch PracticeDKT = new StopWatch();
		StopWatch LaunchDKT = new StopWatch();
		
		System.out.println("before initializing perf dB");
		DKTNew myPerfDB = new DKTNew();
		System.out.println("after initializing perf dB");
			driver= new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			System.out.println(myPerfDB.connectDB("jdbc:mysql://atnsw-bench006:3306/monitoringdb", "perfmon", "123"));
			System.out.println(myPerfDB.initializeScriptExec("Driver Knowledge Test", "Roads and Maritime Services"));
			
			//start stop watch - home
			System.out.println("before RMSHOME start");
			RMSHome.start();
			System.out.println("after RMSHOME start");
			driver.get("http://www.rms.nsw.gov.au/index.html");
			//Assert.assertEquals(driver.findElement(By.linkText("Customer Charter")).getText(), expStr1);
			System.out.println("before RMSHOME stop");
			RMSHome.stop();
			System.out.println("after RMSHOME stop");
			double res_time_sec1 = (double) RMSHome.getTime();
			res_time_sec1 = res_time_sec1/1000;
			System.out.println(res_time_sec1);
			
		System.out.println(myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "RMS Home Page", "RMS Home Page", "Pass", "Successfully executed", res_time_sec1));
		System.out.println("successfully entry 1");
			
			//start stop watch for practice DKT
			System.out.println("before Drivertest start");	
			DriverTest.start();
			
			
			int wait = 10;
			//WebDriverWait WAIT = new WebDriverWait(driver,wait);
			  //  WAIT.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Practice driver knowledge test")));
			//WebElement element = WAIT.until(ExpectedConditions.elementToBeClickable(By.linkText("Practice driver knowledge test")));
			//element.click();
			driver.findElement(By.linkText("Practice driver knowledge test")).click();
			System.out.println("Entered Practice DKT");
			//String str4 = driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText();
			//System.out.println(str4);
			//Assert.assertEquals( driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText(), expStr4);
			DriverTest.stop();
			System.out.println("after Drivertest stop");
			
			double res_time_sec2 = (double) DriverTest.getTime();
			res_time_sec2 = res_time_sec2/1000;
			System.out.println(res_time_sec2);
			if( driver.findElement(By.xpath("//h1[@class='heading-middle']")).getText().equals(expStr4)){				
				
				myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test", "Driver Knowledge Test", "Pass", "Successfully executed", res_time_sec2);
				System.out.println("successfully entry 2");
			}
				else{
				myPerfDB.recordStepExecFailure("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test", "Driver Knowledge Test", "Fail", "Page not found");
				myPerfDB.updateScriptExecutionFailure("Roads and Maritime Services", "Driver Knowledge Test", "Driver Knowledge Test");
				System.out.println("unsuccessfully entry 2");
				myPerfDB.closeConnections();
				driver.close();
			}
			PracticeDKT.start();
			driver.findElement(By.linkText("Practice DKT")).click();
			PracticeDKT.stop();
			
			double res_time_sec3 = (double) PracticeDKT.getTime();
			res_time_sec3 = res_time_sec3/1000;
			System.out.println(res_time_sec3);
			myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Practice DKT", "Practice DKT", "Pass", "Successfully executed", res_time_sec3);
			System.out.println("successfully entry 3");
			
			LaunchDKT.start();
			driver.findElement(By.linkText("Launch the free online practice test")).click();
			String str5=driver.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[1]/tbody/tr[3]/td/font/b")).getText();
			//System.out.println(str5);
			//Assert.assertEquals(str5, expStr5);
			
			LaunchDKT.stop();
			
			double res_time_sec4 = (double) LaunchDKT.getTime();
			res_time_sec4 = res_time_sec4/1000;
			System.out.println(res_time_sec4);
		
				myPerfDB.recordStepResponseTime ("Roads and Maritime Services", "Driver Knowledge Test", "Launch online practise test", "Launch online practise test", "Pass", "Successfully executed", res_time_sec4);
				System.out.println("successfully entry 4");
				
			System.out.println("Updating Scripts in Jenkins");	
			myPerfDB.updateScriptExecSuccess("Driver Knowledge Test", "Roads and Maritime Services");
			myPerfDB.closeConnections();
			
		//}
		//@AfterTest
		//public void CloseDKT() throws InterruptedException{
			Thread.sleep(4000);
			driver.close();
	}

}
