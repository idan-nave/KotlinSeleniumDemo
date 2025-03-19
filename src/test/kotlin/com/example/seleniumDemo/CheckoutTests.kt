package com.example.seleniumDemo

import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * SeleniumDemoTest combines tests for login, product navigation, and checkout.
 * It inherits setup and teardown from BaseTest.
 */
class CheckoutTests : BaseTest() {

    @Test
    fun `test checkout process`() {
        // Login and add a product
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()
        wait.until { driver.findElement(By.className("inventory_item")) }
        driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]")).click()

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click()
        wait.until { driver.findElement(By.className("cart_item")) }

        // Proceed to checkout
        driver.findElement(By.id("checkout")).click()

        // Fill in checkout information
        wait.until { driver.findElement(By.id("first-name")) }
        driver.findElement(By.id("first-name")).sendKeys("Test")
        driver.findElement(By.id("last-name")).sendKeys("User")
        driver.findElement(By.id("postal-code")).sendKeys("12345")
        driver.findElement(By.id("continue")).click()

        // Complete the checkout process
        wait.until { driver.findElement(By.id("finish")) }
        driver.findElement(By.id("finish")).click()

        // Verify successful order completion
        val completeHeader = wait.until { driver.findElement(By.className("complete-header")) }
        assertEquals("Thank you for your order!", completeHeader.text)
    }
}
