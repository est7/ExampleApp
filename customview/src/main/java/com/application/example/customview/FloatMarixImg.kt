package com.application.example.customview

/**
 * @author: est7
 * @date: 2022/12/29
 */
object FloatMarixImg {

    fun getList(): List<FloatArray> {
        return floats

    }

    // 黑白
    val colormatrix_heibai = floatArrayOf(
        0.8f,
        1.6f,
        0.2f,
        0f,
        -163.9f,
        0.8f,
        1.6f,
        0.2f,
        0f,
        -163.9f,
        0.8f,
        1.6f,
        0.2f,
        0f,
        -163.9f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 怀旧
    val colormatrix_huaijiu = floatArrayOf(
        0.2f,
        0.5f,
        0.1f,
        0f,
        40.8f,
        0.2f,
        0.5f,
        0.1f,
        0f,
        40.8f,
        0.2f,
        0.5f,
        0.1f,
        0f,
        40.8f,
        0f,
        0f,
        0f,
        1f,
        0f
    )

    // 哥特
    val colormatrix_gete = floatArrayOf(
        1.9f,
        -0.3f,
        -0.2f,
        0f,
        -87.0f,
        -0.2f,
        1.7f,
        -0.1f,
        0f,
        -87.0f,
        -0.1f,
        -0.6f,
        2.0f,
        0f,
        -87.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 淡雅
    val colormatrix_danya = floatArrayOf(
        0.6f,
        0.3f,
        0.1f,
        0f,
        73.3f,
        0.2f,
        0.7f,
        0.1f,
        0f,
        73.3f,
        0.2f,
        0.3f,
        0.4f,
        0f,
        73.3f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 蓝调
    val colormatrix_landiao = floatArrayOf(
        2.1f,
        -1.4f,
        0.6f,
        0.0f,
        -71.0f,
        -0.3f,
        2.0f,
        -0.3f,
        0.0f,
        -71.0f,
        -1.1f,
        -0.2f,
        2.6f,
        0.0f,
        -71.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f
    )

    // 光晕
    val colormatrix_guangyun = floatArrayOf(
        0.9f,
        0f,
        0f,
        0f,
        64.9f,
        0f,
        0.9f,
        0f,
        0f,
        64.9f,
        0f,
        0f,
        0.9f,
        0f,
        64.9f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 梦幻
    val colormatrix_menghuan = floatArrayOf(
        0.8f,
        0.3f,
        0.1f,
        0.0f,
        46.5f,
        0.1f,
        0.9f,
        0.0f,
        0.0f,
        46.5f,
        0.1f,
        0.3f,
        0.7f,
        0.0f,
        46.5f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f
    )

    // 酒红
    val colormatrix_jiuhong = floatArrayOf(
        1.2f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.9f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.8f,
        0.0f,
        0.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 胶片
    val colormatrix_fanse = floatArrayOf(
        -1.0f,
        0.0f,
        0.0f,
        0.0f,
        255.0f,
        0.0f,
        -1.0f,
        0.0f,
        0.0f,
        255.0f,
        0.0f,
        0.0f,
        -1.0f,
        0.0f,
        255.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f
    )

    // 湖光掠影
    val colormatrix_huguang = floatArrayOf(
        0.8f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.9f,
        0.0f,
        0.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 褐片
    val colormatrix_hepian = floatArrayOf(
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.8f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.8f,
        0.0f,
        0.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 复古
    val colormatrix_fugu = floatArrayOf(
        0.9f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.8f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.5f,
        0.0f,
        0.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 泛黄
    val colormatrix_huan_huang = floatArrayOf(
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.0f,
        0.5f,
        0.0f,
        0.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 传统
    val colormatrix_chuan_tong = floatArrayOf(
        1.0f,
        0.0f,
        0.0f,
        0f,
        -10f,
        0.0f,
        1.0f,
        0.0f,
        0f,
        -10f,
        0.0f,
        0.0f,
        1.0f,
        0f,
        -10f,
        0f,
        0f,
        0f,
        1f,
        0f
    )

    // 胶片2
    val colormatrix_jiao_pian = floatArrayOf(
        0.71f,
        0.2f,
        0.0f,
        0.0f,
        60.0f,
        0.0f,
        0.94f,
        0.0f,
        0.0f,
        60.0f,
        0.0f,
        0.0f,
        0.62f,
        0.0f,
        60.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 锐色
    val colormatrix_ruise = floatArrayOf(
        4.8f,
        -1.0f,
        -0.1f,
        0f,
        -388.4f,
        -0.5f,
        4.4f,
        -0.1f,
        0f,
        -388.4f,
        -0.5f,
        -1.0f,
        5.2f,
        0f,
        -388.4f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 清宁
    val colormatrix_qingning = floatArrayOf(
        0.9f, 0f, 0f, 0f, 0f, 0f, 1.1f, 0f, 0f, 0f, 0f, 0f, 0.9f, 0f, 0f, 0f, 0f, 0f, 1.0f, 0f
    )

    // 浪漫
    val colormatrix_langman = floatArrayOf(
        0.9f,
        0f,
        0f,
        0f,
        63.0f,
        0f,
        0.9f,
        0f,
        0f,
        63.0f,
        0f,
        0f,
        0.9f,
        0f,
        63.0f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

    // 夜色
    val colormatrix_yese = floatArrayOf(
        1.0f,
        0.0f,
        0.0f,
        0.0f,
        -66.6f,
        0.0f,
        1.1f,
        0.0f,
        0.0f,
        -66.6f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
        -66.6f,
        0.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f
    )

    private val floats = mutableListOf(
        colormatrix_heibai,
        colormatrix_huaijiu,
        colormatrix_gete,
        colormatrix_danya,
        colormatrix_landiao,
        colormatrix_guangyun,
        colormatrix_menghuan,
        colormatrix_jiuhong,
        colormatrix_fanse,
        colormatrix_huguang,
        colormatrix_hepian,
        colormatrix_fugu,
        colormatrix_huan_huang,
        colormatrix_chuan_tong,
        colormatrix_jiao_pian,
        colormatrix_ruise,
        colormatrix_qingning,
        colormatrix_langman,
        colormatrix_yese,
    )
}