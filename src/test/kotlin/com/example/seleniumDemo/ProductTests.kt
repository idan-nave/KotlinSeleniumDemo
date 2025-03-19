package com.example.seleniumDemo

import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * ProductTests contains tests for product listing, adding products to the cart,
 * and managing the cart.
 */
class ProductTests : BaseTest() {

    @Test
    fun `test product navigation and add to cart`() {
        // Login first
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()

        // Wait for products to load and add first product to cart
        wait.until { driver.findElement(By.className("inventory_item")) }
        val addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]"))
        addToCartButton.click()

        // Verify the cart badge shows the correct count
        val cartBadge = wait.until { driver.findElement(By.className("shopping_cart_badge")) }
        assertEquals("1", cartBadge.text)
    }

    @Test
    fun `test remove product from cart`() {
        // Login and add a product to the cart
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()
        wait.until { driver.findElement(By.className("inventory_item")) }
        driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]")).click()

        // Navigate to the cart
        driver.findElement(By.className("shopping_cart_link")).click()
        wait.until { driver.findElement(By.className("cart_item")) }

        // Remove the product from the cart
        driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click()

        // Verify the cart is empty
        val cartItems = driver.findElements(By.className("cart_item"))
        assertEquals(0, cartItems.size)
    }

    @Test
    fun `test add multiple products to cart`() {
        // Login first
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()

        // Add two products to the cart
        wait.until { driver.findElement(By.className("inventory_item")) }
        driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[1]")).click()
        driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])[2]")).click()

        // Verify the cart badge shows the correct count
        val cartBadge = wait.until { driver.findElement(By.className("shopping_cart_badge")) }
        assertEquals("2", cartBadge.text)
    }

    @Test
    fun `test product details page`() {
        // Login first
        driver.get("https://www.saucedemo.com/")
        driver.findElement(By.id("user-name")).sendKeys("standard_user")
        driver.findElement(By.id("password")).sendKeys("secret_sauce")
        driver.findElement(By.id("login-button")).click()

        // Click on the first product
        wait.until { driver.findElement(By.className("inventory_item")) }
        driver.findElement(By.className("inventory_item_name")).click()

        // Verify that the product details page is displayed
        val productDetails = wait.until { driver.findElement(By.className("inventory_details_name")) }
        assertTrue(productDetails.isDisplayed)
    }
}
