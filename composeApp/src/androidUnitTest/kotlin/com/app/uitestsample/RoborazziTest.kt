package com.app.uitestsample

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziRule
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

    // Roborazziの共通ルール定義
    @get:Rule
    val roborazziRule = RoborazziRule(
        composeRule = composeTestRule,
        captureRoot = composeTestRule.onRoot(),
        options = RoborazziRule.Options(
            // 出力先を screenshots フォルダに一括指定
            outputDirectoryPath = "screenshots"
        )
    )

    @Test
    fun captureAppScreenshot() {
        composeTestRule.setContent {
            App()
        }
        composeTestRule.onRoot().captureRoboImage()
    }

    @Test
    fun captureAppAnimationGif() {
        composeTestRule.setContent {
            App()
        }

        composeTestRule.onRoot().captureRoboGif(
            composeRule = composeTestRule,
        ) {
            composeTestRule.onNodeWithTag("toggle_button").performClick()
        }
    }
}
