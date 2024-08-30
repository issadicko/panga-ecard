package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date
import java.util.UUID

@Table("wallet")
data class Wallet(
    @Id
    val uuid: UUID,
    val balance: Double,
    val ownerUuid: UUID,
    val createdAt: Date
)