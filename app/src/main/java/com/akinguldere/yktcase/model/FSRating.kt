package com.akinguldere.yktcase.model

/*
  "rating": {
    "rate": 3.9,
    "count": 120
  }
 */

data class FSRating(
    val rate: Double,
    val count: Int
) : java.io.Serializable