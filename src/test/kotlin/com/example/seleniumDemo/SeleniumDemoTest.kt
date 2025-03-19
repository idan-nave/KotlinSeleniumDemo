package com.example.seleniumDemo

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SeleniumDemoTest {

    private lateinit var driver: WebDriver
    private lateinit var wait: WebDriverWait

    @BeforeEach
    fun setUp() {
        val options = ChromeOptions()
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

    @Test
    fun `test successful login`() {
        driver.get("https://www.saucedemo.com/")
        
        // Enter credentials
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()
        
        // Verify login success
        val inventoryContainer = wait.until {
            driver.findElement(By.id("inventory_container"))
        }
        assertTrue(inventoryContainer.isDisplayed)
    }

    @Test
    fun `test product navigation and add to cart`() {
        // Login first
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()
        
        // Add first product to cart
        wait.until {
            driver.findElement(By.className("inventory_item"))
        }
        val addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]"))
        addToCartButton.click()
        
        // Verify cart badge
        val cartBadge = wait.until {
            driver.findElement(By.className("shopping_cart_badge"))
        }
        assertEquals("1", cartBadge.text)
    }

    @Test
    fun `test checkout process`() {
        // Login and add product
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()
        wait.until {
            driver.findElement(By.className("inventory_item"))
        }
        driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]")).click()
        
        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click()
        wait.until {
            driver.findElement(By.className("cart_item"))
        }
        
        // Checkout
        driver.findElement(By.id("checkout")).click()
        
        // Fill checkout info
        wait.until {
            driver.findElement(By.id("first-name"))
        }
        driver.findElement(By.id("first-name")).sendKeys("Test")
        driver.findElement(By.id("last-name")).sendKeys("User")
        driver.findElement(By.id("postal-code")).sendKeys("12345")
        driver.findElement(By.id("continue")).click()
        
        // Finish checkout
        wait.until {
            driver.findElement(By.id("finish"))
        }
        driver.findElement(By.id("finish")).click()
        
        // Verify completion
        val completeHeader = wait.until {
            driver.findElement(By.className("complete-header"))
        }
        assertEquals("Thank you for your order!", completeHeader.text)
    }
}
