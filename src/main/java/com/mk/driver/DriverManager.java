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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.mk.constants.FrameworkConstants;
import com.mk.enums.ConfigProperties;
import com.mk.exceptions.FrameworkException;

public class DriverManager {

	private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	private static String CONFIG_PATH = FrameworkConstants.getConfigfilepath();
	private static String GRIDURL = FrameworkConstants.getGridurl();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();

	// Private constructor

	private DriverManager() {
		throw new IllegalStateException("Utility class- cannot be instantiated");
	}

	private static Properties properties = new Properties();

	/*
	 * Instead of using a method we are using static block Assume you have 100
	 * properties then the method will open the file 100 times but if we put it in
	 * static block it will intialized once the jvm calls the class and cache the
	 * data from properties file.
	 */
	
	static {
		try {
			FileInputStream file = new FileInputStream(CONFIG_PATH);
			properties.load(file);

			properties.forEach(
					(key, value) -> CONFIGMAP.put(String.valueOf(key).toLowerCase(), String.valueOf(value).trim()));

		} catch (IOException e) {
			throw new FrameworkException("Failed to load configuration file",e);
		}
	}

	public static String getValue(ConfigProperties key) {
		if (Objects.isNull(CONFIGMAP.get(key.toString().toLowerCase()))
				|| Objects.isNull(key.toString().toLowerCase())) {
			throw new IllegalArgumentException(
					"Property name " + key + " is not found. Please Check config.properties");
		}
		return CONFIGMAP.get(key.name().toLowerCase());
	}

	public static WebDriver setDriver() {
		if (Objects.isNull(driverThreadLocal.get())) {
			synchronized (DriverManager.class) {
				if (driverThreadLocal.get() == null) {
					if ("local".equalsIgnoreCase(getValue(ConfigProperties.ENV))) {
						System.out.println("Using Local for execution");
						switch (getValue(ConfigProperties.DRIVER).toLowerCase()) {
						case "chrome":
							driverThreadLocal.set(new ChromeDriver());
							break;
						case "edge":
							driverThreadLocal.set(new EdgeDriver());
							break;
						default:
							throw new FrameworkException("Unsuported Driver");
						}
					} else if ("remote".equalsIgnoreCase(getValue(ConfigProperties.ENV))) {
						switch (getValue(ConfigProperties.DRIVER).toLowerCase()) {
						case "chrome":
							driverThreadLocal.set(createRemoteWebDriver(chromeCapabilities()));
							break;
						case "edge":
							driverThreadLocal.set(createRemoteWebDriver(edgeCapabilities()));
							break;
						default:
							throw new FrameworkException("Unsuported browser");
						}
					}
				}
			}
		}
		return driverThreadLocal.get();
	}

	public static WebDriver getDriver() {
		return setDriver();
	}

	private static WebDriver createRemoteWebDriver(DesiredCapabilities cp) {
		try {
			return new RemoteWebDriver(new URL(GRIDURL), cp);
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
		if (driverThreadLocal.get() != null) {
			driverThreadLocal.get().quit();
			driverThreadLocal.remove();
		}
	}

}
