# BombpartyBot
An improved version of my old python bot, now in java, based off msesjrl's python selenium bot

# How to run
**If you do not have java installed, you should be able to simply double click the Bombparty.exe (not tested) or Bombparty file located in the pre-built folder.**

(note: pre-builts are out-dated and most likely wont download due to file size, just run the .jar file)

If you have java installed or the pre-built binaries dont work, you can double-click **Bombparty-bot.jar** and it should run.

You can also open a terminal or cmd in the folder and run:
```
java -jar Bombparty-bot.jar
```

If you dont have java you can get it for free at https://www.oracle.com/java/technologies/downloads/#jdk17-windows

# Errors
If you run the bot with the terminal and get chromedriver errors then open your google chrome and check what version (9x.xxx...) you have. Then download that version from https://chromedriver.chromium.org/downloads and replace the one in provided in your OS' folder. For example if you have version 92.0.4515.159 you would download the ChromeDriver for version 92.

~~There currently seems to be a bug where the code will try and use the mac chromedriver even if you are on windows. I'll try and fix this soon but for now just move the chromedriver.exe from the windows folder into the mac folder and remove the `.exe` at the end.~~
