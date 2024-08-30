package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date
import java.util.UUID

@Table("ecard")
data class Ecard(
    @Id
    val uuid: UUID,
    val issuedAt: Date,
    val validUntil: Date,
    val balance: Double,
) {
}