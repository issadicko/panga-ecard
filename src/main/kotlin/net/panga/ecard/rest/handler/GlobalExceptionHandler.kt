package net.panga.ecard.rest.handler

import net.panga.ecard.rest.dto.ResponseWrapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.springframework.web.reactive.resource.NoResourceFoundException
import reactor.core.publisher.Mono

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error(ex.message ?: "Invalid request"))
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error(ex.message ?: "Internal server error"))
    }

    @ExceptionHandler(NotImplementedError::class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    fun handleNotImplementedError(ex: NotImplementedError): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error(ex.message ?: "Not implemented"))
    }

    @ExceptionHandler(NoResourceFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoResourceFoundException(ex: NoResourceFoundException): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error("Resource not found",404))
    }

    @ExceptionHandler(UnsupportedOperationException::class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    fun handleUnsupportedOperationException(ex: UnsupportedOperationException): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error(ex.message ?: "Not implemented"))
    }

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAccessDeniedException(ex: AccessDeniedException): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error("Access denied",403))
    }

    @ExceptionHandler(Unauthorized::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorizedException(ex: Unauthorized): Mono<ResponseWrapper<Any>> {
        return Mono.just(ResponseWrapper.error("Unauthorized",401))
    }

}