package com.application.others.bean

/**
 *
 * @author: est7
 * @date: 2022/11/23
 */
data class Rank(
    val rank: Int,
    val name: String,
    val count: Int,
    val avatarUrl: String,
    val levelUrl: String,
    val level: Int = 200,
    val tag: String = "达人"
)

data class BetterRank(
    val title: String,
    val rankColumn: String,
    val nameColumn: String,
    val countColumn: String,
    val ranks: List<Rank>
)

val betterRank = BetterRank(
    "排名", "主播", "粉丝数", "本周新增", listOf(
        Rank(
            1,
            "小红",
            19203,
            "https://s7d1.scene7.com/is/image/PETCO/new-kitten-guide-090517-cat-featured-355w-200h-d",
            ""
        ), Rank(
            2,
            "小红",
            309293,
            "https://static.boredpanda.com/blog/wp-content/uploads/2018/04/5acb63d83493f__700-png.jpg",
            ""
        ), Rank(
            3,
            "小里",
            193930,
            "https://www.maxpixel.net/static/photo/1x/Cute-Domestic-Cat-Cat-Face-Pet-Cat-3378397.jpg",
            ""
        ), Rank(
            4,
            "小腾",
            1999990,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1200px-Cat03.jpg",
            ""
        ), Rank(
            5,
            "天青色",
            19939330,
            "https://www.telegraph.co.uk/content/dam/pets/2017/01/06/1-JS117202740-yana-two-face-cat-news_trans_NvBQzQNjv4BqJNqHJA5DVIMqgv_1zKR2kxRY9bnFVTp4QZlQjJfe6H0.jpg?imwidth=450",
            ""
        ), Rank(
            6,
            "等烟雨来,就是不来",
            19090990,
            "http://catsatthestudios.com/wp-content/uploads/2017/12/12920541_1345368955489850_5587934409579916708_n-2-960x410.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ), Rank(
            7,
            "急死你,我就不来",
            190,
            "http://theorphanpet.com/wp-content/uploads/2016/06/13233136_788779574558512_3397930498291044113_n.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ), Rank(
            8,
            "你拿我怎么办呢,嘿嘿",
            190,
            "http://www.royalcanin.ca/~/media/Royal-Canin-Canada/Product-Categories/cat-adult-landing-hero.ashx",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ), Rank(
            9,
            "不来就不来,爱来不来",
            138239,
            "https://i2-prod.mirror.co.uk/incoming/article11935245.ece/ALTERNATES/s615/SWNS_SELFIE_CATS_011.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ),Rank(
            9,
            "不来就不来,爱来不来",
            138239,
            "https://i2-prod.mirror.co.uk/incoming/article11935245.ece/ALTERNATES/s615/SWNS_SELFIE_CATS_011.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ),Rank(
            9,
            "不来就不来,爱来不来",
            138239,
            "https://i2-prod.mirror.co.uk/incoming/article11935245.ece/ALTERNATES/s615/SWNS_SELFIE_CATS_011.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ),Rank(
            9,
            "不来就不来,爱来不来",
            138239,
            "https://i2-prod.mirror.co.uk/incoming/article11935245.ece/ALTERNATES/s615/SWNS_SELFIE_CATS_011.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        ),Rank(
            9,
            "不来就不来,爱来不来",
            138239,
            "https://i2-prod.mirror.co.uk/incoming/article11935245.ece/ALTERNATES/s615/SWNS_SELFIE_CATS_011.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2729107397,409883431&fm=26&gp=0.jpg"
        )
    )
)
