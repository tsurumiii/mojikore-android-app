package com.volare.mojikore.persistence

import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

}