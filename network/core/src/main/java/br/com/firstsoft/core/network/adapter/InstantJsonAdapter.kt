package br.com.firstsoft.core.network.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant

internal object InstantJsonAdapter {
    @ToJson
    fun toJson(instant: Instant): String = instant.toString()

    @FromJson
    fun fromJson(value: String): Instant = Instant.parse(value)
}
