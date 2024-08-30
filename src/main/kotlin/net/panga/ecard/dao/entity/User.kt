package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID


@Table("`user`")
data class User(
    @Id
    val uuid: UUID,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
)