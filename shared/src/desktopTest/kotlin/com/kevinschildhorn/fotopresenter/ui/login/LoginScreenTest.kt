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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
Testing [com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreen]
 Uses [com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler]
 **/
class LoginScreenTest : KoinTest {
    private val viewModel: LoginViewModel by inject()

    @Before
    fun startTest() {
        startKoin {
            modules(testingModule())
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun signingInSuccess() = runComposeUiTest {
        var loggedIn = false
        setContent {
            LoginScreen(viewModel) {
                loggedIn = true
            }
        }

        // Initial State
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertTextEquals("Log In")

        // Go through every input and make sure login is only enabled after all is filled
        onNodeWithTag(TestTags.Login.HOST_NAME).performTextInput("192.168.1.1")
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

        onNodeWithTag(TestTags.Login.USERNAME).performTextInput("admin")
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

        onNodeWithTag(TestTags.Login.PASSWORD).performTextInput("password")
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

        onNodeWithTag(TestTags.Login.SHARED_FOLDER).performTextInput("Public")
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()

        // Testing toggle
        onNodeWithTag(TestTags.Login.AUTO_CONNECT).performClick()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()
        onNodeWithTag(TestTags.Login.AUTO_CONNECT).performClick()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()

        // Remove credentials
        onNodeWithTag(TestTags.Login.HOST_NAME).performTextClearance()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsNotEnabled()

        // Login
        assertFalse(loggedIn)
        onNodeWithTag(TestTags.Login.HOST_NAME).performTextInput("192.168.1.1")
        onNodeWithTag(TestTags.Login.AUTO_CONNECT).performClick()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled().performClick()
        waitForIdle()
        assertTrue(loggedIn)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun signingInError() = runComposeUiTest {
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
    fun viewingThePassword() = runComposeUiTest {
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

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun clickTest() = runComposeUiTest {
        setContent {
            LoginScreen(viewModel) {}
        }

        onNodeWithTag(TestTags.Login.LINK).performClick()
    }
}