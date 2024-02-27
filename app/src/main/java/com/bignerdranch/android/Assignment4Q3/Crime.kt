package com.bignerdranch.android.Assignment4Q3

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import java.util.Date

@Entity
data class Crime(
    @PrimaryKey val id: UUID,
    val amount: String,
    val category: String,
    val date: Date,
)
