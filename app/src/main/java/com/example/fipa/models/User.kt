package com.example.fipa.models

import java.util.*

/**
 * Created by KING JINHO on 2020-01-08
 */
data class User(
    private var email: String,
    private var password: String,
    private var businessLicense: String,
    private var yn_user: Boolean,
    private var yn_auth: Boolean,
    private var cd_token: String
) {

    constructor() : this("", "", "", false, false, "")

    constructor(email: String, password: String, license: String) : this(email, password, license, false, false, "")



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

    public fun getBusinessLicense(): String {
        return businessLicense
    }

}