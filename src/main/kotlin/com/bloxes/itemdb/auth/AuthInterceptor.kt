package com.bloxes.itemdb.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

class AuthInterceptor: WebRequestInterceptor {

    @Value("\${environment.secret}")
    private lateinit var secretKey: String

    override fun preHandle(request: WebRequest) {
        println(secretKey)
        println("interceptor is running")
//        println(request.getHeader("SECRET"))
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {

    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {

    }
}