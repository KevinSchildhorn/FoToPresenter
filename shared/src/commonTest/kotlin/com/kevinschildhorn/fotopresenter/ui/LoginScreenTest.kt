package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class LoginScreenTest : KoinTest {

    private val viewModel: LoginViewModel by inject()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loginCredentialsEntry() = runComposeUiTest {
        startKoin {
            modules(testingModule())
        }

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
    fun loginNavigation() = runComposeUiTest {
        startKoin {
            modules(testingModule())
        }

        setContent {
            LoginScreen(viewModel) {}
        }

        onNodeWithTag(TestTags.Login.HOST_NAME).performTextInput("192.168.1.1")
        onNodeWithTag(TestTags.Login.USERNAME).performTextInput("admin")
        onNodeWithTag(TestTags.Login.PASSWORD).performTextInput("password")
        onNodeWithTag(TestTags.Login.SHARED_FOLDER).performTextInput("Public")
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).assertIsEnabled()
        onNodeWithTag(TestTags.Login.LOGIN_BUTTON).performClick()
        //onNodeWithTag("DirectoryNavigationBar").assertExists()
    }
}