package com.app.uitestsample

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboGif
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel7, sdk = [34])
class RoborazziTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // 指定したComposeのスクリーンショットを撮る
    @Test
    fun captureAppScreenshot() {
        composeTestRule.setContent {
            App()
        }

        composeTestRule.onRoot().captureRoboImage()
    }

    // 指定したComposeのアニメーションをGIFで撮る
    @Test
    fun captureAppAnimationGif() {
        composeTestRule.setContent {
            App()
        }

        composeTestRule.onRoot().captureRoboGif(
            composeRule = composeTestRule,
        ) {
            composeTestRule.onNodeWithText("Click me!").performClick()
        }
    }
}
