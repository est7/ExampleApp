package com.application.others.deeplink

import android.net.Uri

object UrlGenerator {
    fun generateUrl(path: String?, params: Map<String, Any>): String {
        val paramsList: MutableList<String> = ArrayList()
        for ((key, value1) in params) {
            val value = value1.toString()
            paramsList.add("$key=$value")
        }

        val sb = StringBuilder()
        for (index in paramsList.indices) {
            sb.append(paramsList[index])
            if (index < paramsList.size - 1) {
                sb.append("&")
            }
        }
        val paramsStr = sb.toString()
        return Uri.Builder().scheme("xiaoma").encodedAuthority("store.xiaoma.com")
            .encodedPath("gate").appendQueryParameter("path", path)
            .appendQueryParameter("params", paramsStr).build().toString()
    }
}