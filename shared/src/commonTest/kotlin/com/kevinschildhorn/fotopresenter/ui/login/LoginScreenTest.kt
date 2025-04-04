package com.kevinschildhorn.fotopresenter.ui.login

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
Testing [com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreen]
 **/
class LoginScreenTest : KoinTest {
    private val viewModel: LoginViewModel by inject()

    @BeforeTest
    fun startTest() {
        startKoin {
            modules(testingModule())
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun signingInSuccess() =
        runComposeUiTest {
            setContent {
                LoginScreen(viewModel) {}
            }

            // Initial State
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertTextEquals("Log In")

            // Go through every input and make sure login is only enabled after all is filled
            onNodeWithTag(TestTags.Login.HOST_NAME).performTextInput("google.com")
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

            onNodeWithTag(TestTags.Login.USERNAME).performTextInput("John")
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

            onNodeWithTag(TestTags.Login.PASSWORD).performTextInput("Password")
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

            onNodeWithTag(TestTags.Login.SHARED_FOLDER).performTextInput("MyFolder")
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()

            // Testing toggle
            onNodeWithTag(TestTags.Login.AUTO_CONNECT).performClick()
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()
            onNodeWithTag(TestTags.Login.AUTO_CONNECT).performClick()
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()

            // Remove credentials
            onNodeWithTag(TestTags.Login.HOST_NAME).performTextClearance()
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun signingInError() =
        runComposeUiTest {
            setContent {
                LoginScreen(viewModel) {}
            }

            // Entered incorrect details
            onNodeWithTag(TestTags.Login.HOST_NAME).performTextInput("wrong")
            onNodeWithTag(TestTags.Login.USERNAME).performTextInput("wrong")
            onNodeWithTag(TestTags.Login.PASSWORD).performTextInput("wrong")
            onNodeWithTag(TestTags.Login.SHARED_FOLDER).performTextInput("wrong")
            with(onNodeWithTag(TestTags.Login.LOGIN_BUTTON)) {
                assertIsEnabled()
                performClick()
            }
            onNodeWithTag(TestTags.ERROR_VIEW).let {
                it.assertExists()
                it.assertTextContains("Error Occurred! ", ignoreCase = true)
            }
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()
            with(onNodeWithTag(TestTags.Login.HOST_NAME)) {
                performTextClearance()
                performTextInput("Hi")
            }
            onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()
            onNodeWithTag(TestTags.ERROR_VIEW).assertDoesNotExist()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun viewingThePassword() =
        runComposeUiTest {
            setContent {
                LoginScreen(viewModel) {}
            }

            val passwordNode = onNodeWithTag(TestTags.Login.PASSWORD)
            passwordNode.performTextInput("abc123")
            passwordNode.assertTextEquals("••••••")
            onNodeWithTag(TestTags.Login.HIDE_SHOW_PASSWORD).performClick()
            passwordNode.assertTextEquals("abc123")
            onNodeWithTag(TestTags.Login.HIDE_SHOW_PASSWORD).performClick()
            passwordNode.assertTextEquals("••••••")
        }
}
