package com.hojongs.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "items")
data class Item(
    @Id
    val id: Int,
    val name: String
)
