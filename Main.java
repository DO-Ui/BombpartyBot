import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) throws IOException {
		String CURR_DIR = System.getProperty("user.dir");
		if (System.getProperty("os.name").equals("Linux")) {
			File chromeFile = new File(CURR_DIR + "/linux/chromedriver");
			System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
		} else if (System.getProperty("os.name").equals("Windows")) {
			File chromeFile = new File(CURR_DIR + "/windows/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
		} else {
			File chromeFile = new File(CURR_DIR + "/mac/chromedriver");
			System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
		}
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.jklm.fun");
		driver.manage().window().maximize();

		Path filePath = new File(CURR_DIR + "/wordlist.txt").toPath();

		Charset charset = Charset.defaultCharset();

		List<String> stringList = Files.readAllLines(filePath, charset);
		String[] wordlist = stringList.toArray(new String[] {});

		while (true) {
			if ((driver.findElements(By.xpath("/html/body/div[2]/div[4]/div[1]/iframe")).size() > 0)) {
				try {
					driver.switchTo().frame(0); // Switch to correct iframe
					if (!(driver.findElements(By.xpath("/html/body/div[2]/div[3]/div[1]/div[1]/button"))
							.size() > 0)) {
						driver.switchTo().parentFrame();
					}
				} catch (Exception e) {
				}
			}

			try {
				if (!driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]")).isDisplayed()) {
					if (driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[1]/button")).isDisplayed()) {
						driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[1]/button")).click(); // Auto join
					}
					LinkedList<String> listOfWords = new LinkedList<>();

					String syllable = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[2]/div"))
							.getText().toLowerCase();

					System.out.println(syllable);

					WebElement answerBox = driver
							.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[2]/form/input"));

					for (String line : wordlist) {
						if (line.contains(syllable)) {
							listOfWords.add(line);
						}
					}

					int random = (int) (Math.random() * (listOfWords.size() - 0) + 0);

					String matchingWord = listOfWords.get(random);

					answerBox.click();

					// TimeUnit.SECONDS.sleep((int) (Math.random() * (65 - 15) + 15) / 100);

					for (char character : matchingWord.toCharArray()) {
						int fail = (int) (Math.random() * (1000 - 0) + 0);
						if (fail == 2) {
							int loops = (int) (Math.random() * (3 - 0) + 0);

							for (int i = 0; i < loops; i++) {
								answerBox.sendKeys(Character.toString((char) ('A' + Math.random() * ('Z' - 'A' + 1))));
								TimeUnit.SECONDS.sleep((int) (Math.random() * (5 - 2) + 2) / 60);
							}

							TimeUnit.MILLISECONDS.sleep(300);

							for (int i = 0; i < loops; i++) {
								answerBox.sendKeys(Keys.BACK_SPACE);
							}

							TimeUnit.MILLISECONDS.sleep(100);

							answerBox.sendKeys(Character.toString(character));

						} else {
							answerBox.sendKeys(Character.toString(character));
							TimeUnit.SECONDS.sleep((int) (Math.random() * (50 - 5) + 5) / 60);
						}
					}
					answerBox.sendKeys(Keys.RETURN);
					TimeUnit.MILLISECONDS.sleep(100);

				}
			} catch (Exception e) {
			}

		}

	}

}
