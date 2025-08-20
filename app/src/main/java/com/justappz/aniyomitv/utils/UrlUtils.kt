package com.justappz.aniyomitv.utils

import com.justappz.aniyomitv.domain.model.streams.StreamsDomain

object UrlUtils {

    /**
     * This method will return the first stream that matches the provided URL provider.
     * If no match is found, it will return null.
     * */
    fun getMatchedStream(stream: List<StreamsDomain>, urlProvider: String): StreamsDomain? {
        return stream.firstOrNull { it.sourceUrl?.contains(urlProvider, ignoreCase = true) == true }
    }
}