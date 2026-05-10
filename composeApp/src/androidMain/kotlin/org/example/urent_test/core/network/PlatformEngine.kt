package org.example.urent_test.core.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun platformHttpEngine(): HttpClientEngineFactory<*> = OkHttp