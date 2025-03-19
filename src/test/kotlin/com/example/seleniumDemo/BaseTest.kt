package com.example.seleniumDemo

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * BaseTest provides the common setup and teardown methods for all Selenium tests.
 * It also configures a toggle for headless mode. 
 * Set the system property 'headless' to "true" or "false" to run tests in headless mode or with UI.
 */
abstract class BaseTest {
    protected lateinit var driver: WebDriver
    protected lateinit var wait: WebDriverWait
    protected val isHeadless: Boolean = System.getProperty("headless", "true").toBoolean()

    @BeforeEach
    fun setUp() {
        val options = ChromeOptions()
        if (isHeadless) {
            options.addArguments("--headless=new")
        }
        options.addArguments(
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--remote-debugging-port=9222"
        )
        driver = ChromeDriver(options)
        wait = WebDriverWait(driver, Duration.ofSeconds(10))
        driver.manage().window().maximize()
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }
}
