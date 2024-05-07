package com.jubayedalam.e_commerce_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CartEntity::class], version = 2, exportSchema = false)
abstract class CartDB : RoomDatabase() {

    abstract fun productDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDB? = null

        fun getDatabase(context: Context): CartDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDB::class.java,
                    "cart_database"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create a new table with the desired schema
        database.execSQL("CREATE TABLE IF NOT EXISTS cart_items_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, productId TEXT NOT NULL, productName TEXT NOT NULL, productPrice TEXT NOT NULL, imageUrl TEXT NOT NULL, quantity INTEGER NOT NULL DEFAULT 0)")

        // Copy data from the old table to the new table
        database.execSQL("INSERT INTO cart_items_new (id, productId, productName, productPrice, imageUrl) SELECT id, productId, productName, productPrice, imageUrl FROM cart_items")

        // Drop the old table
        database.execSQL("DROP TABLE cart_items")

        // Rename the new table to the original table name
        database.execSQL("ALTER TABLE cart_items_new RENAME TO cart_items")
    }
}

