package com.jubayedalam.e_commerce_app.room.fav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FavEntity::class], version = 1, exportSchema = false)
abstract class FavDB : RoomDatabase(){
    abstract fun favDao(): FavDao

    companion object {
        @Volatile
        private var INSTANCE: FavDB? = null

        fun getDatabase(context: Context): FavDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavDB::class.java,
                    "fav_database"
                ).addMigrations(MIGRATION_0_1).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

val MIGRATION_0_1 = object : Migration(0, 1) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create a new table with the desired schema
        database.execSQL("CREATE TABLE IF NOT EXISTS fav_products_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, productId TEXT NOT NULL, productName TEXT NOT NULL, productPrice TEXT NOT NULL, imageUrl TEXT NOT NULL)")

        // Copy data from the old table to the new table
        database.execSQL("INSERT INTO fav_products_new (id, productId, productName, productPrice, imageUrl) SELECT id, productId, productName, productPrice, imageUrl FROM fav_products")

        // Drop the old table
        database.execSQL("DROP TABLE fav_products")

        // Rename the new table to the original table name
        database.execSQL("ALTER TABLE fav_products_new RENAME TO fav_products")
    }
}
