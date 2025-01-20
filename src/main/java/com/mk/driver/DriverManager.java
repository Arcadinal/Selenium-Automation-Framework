package com.mk.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mk.enums.ConfigProperties;
import com.mk.exceptions.FrameworkException;

public class DriverManager {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
	private static final Map<String, String> CONFIGMAP = new HashMap<String, String>();
	private static final String GRIDURL = "http://localhost:4444/wd/hub";

	static {
		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
			Properties properties = new Properties();
			properties.load(file);
			properties.forEach(
					(key, value) -> CONFIGMAP.put(String.valueOf(key).toLowerCase(), String.valueOf(value).trim()));
		} catch (IOException e) {
			throw new FrameworkException("Failed to load config file!");
		}
	}

	public static WebDriver getDriver() {
		return DRIVER.get();
	}

	public static void setDriver(String browser) {
		if (Objects.isNull(DRIVER.get())) {
			synchronized (DriverManager.class) {
				if (DRIVER.get() == null) {
					if ("local".equalsIgnoreCase(getEnvValue(ConfigProperties.ENV))) {
						System.out.println("Using Local Execution!!");
						switch (browser.toLowerCase()) {
						case "chrome":
							DRIVER.set(new ChromeDriver());
							break;
						case "firefox":
							DRIVER.set(new FirefoxDriver());
							break;
						case "edge":
							DRIVER.set(new EdgeDriver());
							break;
						default:
							throw new FrameworkException("unsupported browser: " + browser);
						}
					} else if ("remote".equalsIgnoreCase(getEnvValue(ConfigProperties.ENV))) {
						switch (browser.toLowerCase()) {
						case "chrome":
							DRIVER.set(createRemoteWebDriver(chromeCapabilities()));
							break;
						case "edge":
							DRIVER.set(createRemoteWebDriver(edgeCapabilities()));
							break;
						case "firefox":
							DRIVER.set(createRemoteWebDriver(firefoxCapabilities()));
							break;
						default:
							throw new FrameworkException("Unsuported browser");
						}
					}
				}
			}
		}
	}

	private static String getEnvValue(ConfigProperties key) {
		if (Objects.isNull(CONFIGMAP.get(key.toString().toLowerCase()))
				|| Objects.isNull(key.toString().toLowerCase())) {
			throw new IllegalArgumentException(
					"Property name " + key + " is not found. Please Check config.properties");
		}
		return CONFIGMAP.get(key.name().toLowerCase());
	}

	private static WebDriver createRemoteWebDriver(DesiredCapabilities cp) {
		try {
			return new RemoteWebDriver(new URL(getEnvValue(ConfigProperties.SELENIUMGRIDURL)), cp);
		} catch (MalformedURLException e) {
			throw new FrameworkException("Invalid grid url: " + GRIDURL, e);
		}
	}

	private static DesiredCapabilities edgeCapabilities() {
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setBrowserName("MicrosoftEdge");
		return cp;
	}

	private static DesiredCapabilities chromeCapabilities() {
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setBrowserName("chrome");
		return cp;
	}

	private static DesiredCapabilities firefoxCapabilities() {
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setBrowserName("firefox");
		return cp;
	}

	public static void quitDriver() {
		if (DRIVER.get() != null) {
			DRIVER.get().quit();
			DRIVER.remove();
		}
	}

}
