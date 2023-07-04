package com.alecfut07.gamezone

data class SignInRequest(val email: String, val password: String) {
//    override fun equals(other: Any?): Boolean {
//        val signInRequest = other as? SignInRequest
//        return this.email == signInRequest?.email && this.password == signInRequest.password
//    }
}
