package com.akinguldere.yktcase.model

import com.google.gson.annotations.SerializedName

/*
{
    "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXIiOiJtb3JfMjMxNCIsImlhdCI6MTY3NDk5NzIwNn0.aD16TOcMf-Vy2-qm6rqlBTEjOftdFFlPGK22DdalN5I"
}
 */

data class LoginResponse(
    val token: String
)