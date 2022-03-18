package com.example.tddkata.main

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel()
    }

    @Test
    fun `when both username and password are empty, return empty username and password`(){
        val validation: CredentialValidation = viewModel.validateCredentials("","")
        assertThat(validation).isEqualTo(CredentialValidation.EmptyUserNameAndPassword)
    }

    @Test
    fun `when username is empty and password is not empty, return empty username`(){
        val validation: CredentialValidation = viewModel.validateCredentials("","1234")
        assertThat(validation).isEqualTo(CredentialValidation.EmptyUserName)
    }

    @Test
    fun `when username is not empty and password is empty, return empty password`(){
        val validation: CredentialValidation = viewModel.validateCredentials("rajesh","")
        assertThat(validation).isEqualTo(CredentialValidation.EmptyPassword)
    }

    @Test
    fun `when username is wrong and password is correct, return invalid username`(){
        val validation: CredentialValidation = viewModel.validateCredentials("raj","1234")
        assertThat(validation).isEqualTo(CredentialValidation.InvalidUserName)
    }

    @Test
    fun `when username is correct and password is wrong, return invalid password`(){
        val validation: CredentialValidation = viewModel.validateCredentials("rajesh","12")
        assertThat(validation).isEqualTo(CredentialValidation.InvalidPassword)
    }

    @Test
    fun `when both username and password is wrong , return invalid username and password`(){
        val validation: CredentialValidation = viewModel.validateCredentials("raj","12")
        assertThat(validation).isEqualTo(CredentialValidation.InvalidUserNameAndPassword)
    }

    @Test
    fun `when both username and password is correct , return valid credentials`(){
        val validation: CredentialValidation = viewModel.validateCredentials("rajesh","1234")
        assertThat(validation).isEqualTo(CredentialValidation.ValidCredentials)
    }
}