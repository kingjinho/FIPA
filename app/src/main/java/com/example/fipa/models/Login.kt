package com.example.fipa.models

/**
 * Created by KING JINHO on 2020-01-08
 */
data class Login(
    private var email: String,
    private var password: String,
    private var businessLicense: String
) {

    constructor() : this("", "", "")


    public fun setEmail(email: String) {
        this.email = email
    }

    public fun getEmail(): String {
        return email
    }

    public fun setPassword(password: String) {
        this.password = password
    }

    public fun getPassword(): String {
        return password
    }

    public fun setBusinessLicense(businessLicense: String) {
        this.businessLicense = businessLicense
    }

    public fun getBusinessLicense() : String {
        return businessLicense
    }

}