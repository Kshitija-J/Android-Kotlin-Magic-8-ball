package com.example.magic_eight

import androidx.lifecycle.ViewModel

class MagicView : ViewModel() {
    val responseBank = listOf(
        Response(R.string.res_1),
        Response(R.string.res_2),
        Response(R.string.res_3),
        Response(R.string.res_4),
        Response(R.string.res_5),
        Response(R.string.res_6),
        Response(R.string.res_7),
        Response(R.string.res_8),
        Response(R.string.res_9),
        Response(R.string.res_10),
        Response(R.string.res_11),
        Response(R.string.res_12),
        Response(R.string.res_13),
        Response(R.string.res_14),
        Response(R.string.res_15),
        Response(R.string.res_16),
        Response(R.string.res_17),
        Response(R.string.res_18),
        Response(R.string.res_19),
        Response(R.string.res_20)
    )

    var currentResponse: Response? = null
        private set

    fun getRandomResponse() {
        currentResponse = responseBank.random()
    }

    fun generateNewRandomResponse() {
        currentResponse = responseBank.random()
       }


}