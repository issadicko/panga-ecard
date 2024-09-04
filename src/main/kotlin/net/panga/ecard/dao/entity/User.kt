package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.util.*


@Table("\"user\"")
data class User(
    @Id
    var id: Long?                   = null,
    var uuid: UUID?                 = null,
    var phoneNumber: String?        = null,
    var email: String?              = null,
    private var password: String?   = null,
    var firstname: String?          = null,
    var lastname: String?           = null,
): UserDetails, Serializable{

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("USER"))
    }

    fun setPassword(password: String){
        this.password = password
    }

    override fun getPassword(): String {
        return password?:""
    }

    override fun getUsername(): String {
        return phoneNumber?:""
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}