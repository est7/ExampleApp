package com.application.others.deeplink

import android.net.Uri

object ItemProvider {

    val itemList = listOf(
        "活动专区" to generateUrl("main", mapOf("tabType" to "activities")),

        "皮肤商城" to generateUrl("main", mapOf("tabType" to "skin-store")),

        "形象商城" to generateUrl("main", mapOf("tabType" to "vpa-store")),

        "我的" to generateUrl("main", mapOf("tabType" to "mine")),

        // 1. 因为不再区分"主机"与"仪表"皮肤, 所以不需要考虑皮肤套装, 将suitId更改为skinId
        // 2. 添加Ai推荐的推荐Id(recId)参数
        "皮肤推荐" to generateUrl(
            path = "skinDetail",
            params = mapOf(
                "skinId" to 1L,
                "recId" to "uuid"
            )
        ),

        // 添加形象推荐url示例
        "形象推荐1" to generateUrl(
            path = "vpaPickup",
            params = mapOf(
                "recId" to 1L
            )
        ),

        "形象推荐2" to generateUrl(
            path = "vpaPickup",
            params = mapOf(
                "recId" to 110L
            )
        ),

        )

    // 提供生成Url的方式
    // Java 请查看 UrlGenerator 类
    private fun generateUrl(
        path: String,
        params: Map<String, Any?>
    ) = Uri.Builder().run {
        scheme("xiaoma")
        encodedAuthority("store.xiaoma.com")
        encodedPath("gate")
        appendQueryParameter("path", path)
        appendQueryParameter(
            "params",
            params.entries.joinToString("&") { (key, value) -> "$key=$value" }
        )
        build()
    }.toString()

}