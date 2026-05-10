package org.example.urent_test

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform