package net.panga.ecard.dao.entity

import org.springframework.data.relational.core.mapping.Table


@Table("`user`")
data  class User(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val ecards: List<Ecard>,
    val mainWallet: Wallet
)