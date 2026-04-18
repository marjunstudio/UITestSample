package com.app.uitestsample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform