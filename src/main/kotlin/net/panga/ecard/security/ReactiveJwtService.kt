package net.panga.ecard.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.security.Key
import java.util.*

@Service
class ReactiveJwtService {

    companion object {
        private const val SECRET_KEY = "7336763979244226452948404D635166546A576E5A7234753777217A25432A46"
    }

    fun extractUsername(token: String): Mono<String> =
        extractClaim(token) { obj: Claims -> obj.subject }.toMono()

    fun isTokenValid(token: String, userDetails: UserDetails): Mono<Boolean> =
        extractUsername(token)
            .map { username ->
                username == userDetails.username && !isTokenExpired(token)
            }

    private fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date =
        extractClaim(token) { obj: Claims -> obj.expiration }

    fun generateToken(userDetails: UserDetails): Mono<String> =
        generateToken(HashMap(), userDetails)

    fun generateToken(extractClaims: Map<String, Any>, userDetails: UserDetails): Mono<String> =
        Mono.fromCallable {
            Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.username)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact()
        }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T =
        claimsResolver(extractAllClaims(token))

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}