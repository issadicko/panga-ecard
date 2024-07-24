package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date

@Table("wallet")
data class Wallet(
    @Id
    val id: Long,
    val balance: Double,
    val owner: User,
    val ecard: Ecard,
    val createdAt: Date
)