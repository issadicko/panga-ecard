package net.panga.ecard.rest.dto

data class ResponseWrapper<T>(
    var successful: Boolean,
    var message: String,
    var errorCode: Int? = 0,
    var data: T? = null
){

    fun code(code: Int): ResponseWrapper<T> {
        code.also { errorCode = it }
        return this
    }

    fun message(message: String): ResponseWrapper<T> {
        message.also { this.message = it }
        return this
    }

    fun data(data: T): ResponseWrapper<T> {
        data.also { this.data = it }
        return this
    }

    fun success(): ResponseWrapper<T> {
        successful = true
        return this
    }

    companion object {
        fun <T> success(data: T): ResponseWrapper<T> {
            return ResponseWrapper(true, "Success", 200, data)
        }

        fun <T> success(message: String, data: T): ResponseWrapper<T> {
            return ResponseWrapper(true, message, 200, data)
        }

        fun <T> error(message: String, errorCode: Int): ResponseWrapper<T> {
            return ResponseWrapper(false, message, errorCode)
        }

        fun <T> error(message: String): ResponseWrapper<T> {
            return ResponseWrapper(false, message)
        }

        fun <T> error(errorCode: Int): ResponseWrapper<T> {
            return ResponseWrapper(false, "Error", errorCode)
        }
    }

}
