package org.example.urent_test.core.network

import io.ktor.client.engine.HttpClientEngineFactory

expect fun platformHttpEngine(): HttpClientEngineFactory<*>