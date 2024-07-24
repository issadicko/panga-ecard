package net.panga.ecard.dao.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date

@Table("ecard")
data class Ecard(
    @Id
    val id: Long,
    val issuedAt: Date,
    val validUntil: Date,
    val amount: Double,
) {
}