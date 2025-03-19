package com.example.seleniumDemo

import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import kotlin.test.assertTrue

/**
 * LoginTests contains tests for the login functionality of the Swag Labs demo site.
 */
class LoginTests : BaseTest() {

    @Test
    fun `test successful login`() {
        driver.get("https://www.saucedemo.com/")

        // Enter credentials
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()

        // Verify login success by ensuring the inventory container is displayed
        val inventoryContainer = wait.until { driver.findElement(By.id("inventory_container")) }
        assertTrue(inventoryContainer.isDisplayed)
    }

    @Test
    fun `test failed login with incorrect password`() {
        driver.get("https://www.saucedemo.com/")

        // Enter credentials with incorrect password
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("wrong_password")
        driver.findElement(By.id("login-button")).click()

        // Verify error message is displayed
        val errorMessage = wait.until { driver.findElement(By.xpath("//h3[@data-test='error']")) }
        assertTrue(errorMessage.isDisplayed)
    }
}
