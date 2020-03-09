package com.example.fipa.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fipa.R
import com.example.fipa.extensions.inTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.inTransaction { replace(R.id.fragment_layout, SplashFragment()) }
        /**
         * todo
         * 1. 기본정보
         * 2. 회원가입되어있고 로그인 되어있다면
         * 3. 안되어있을시 로그인 화면
         */
    }

}
