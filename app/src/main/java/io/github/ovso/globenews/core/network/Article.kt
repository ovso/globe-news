package io.github.ovso.globenews.core.network

import kotlinx.serialization.Serializable


@Serializable
data class Article(
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
)


val fakeArticleList = listOf(
    Article(
        publishedAt = "2023-11-24T04:02:57Z",
        title = "오래갈 DDR5 시대 함께할 메모리,ANACOMDA DDR5-6000 CL40 ET RGB White 패키지 - 보드나라 (Bodnara)",
        url = "https://www.bodnara.co.kr/bbs/article.html?num=190498",
        urlToImage = "https://file.bodnara.co.kr/up/news/190498-sumDSC07030.jpg"
    ),
    Article(
        publishedAt = "2023-11-24T04:02:57Z",
        title = "윈도우 11 버전 23H2, 클린 설치시 '내레이터' 작동 오류 - 케이벤치 (KBench)",
        url = "https://kbench.com/?q=node/250142",
        urlToImage = "https://images.kbench.com/kbench/article/thumbnail/250142_thumb.jpg"
    )
)