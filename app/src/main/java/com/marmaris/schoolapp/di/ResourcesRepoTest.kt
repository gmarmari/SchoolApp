package com.marmaris.schoolapp.di

/**
 * Test implementation of the ResourcesRepo Interface.
 * Used in unit testing.
 **/
class ResourcesRepoTest : ResourcesRepo {

    //region strings

    override val stringOnlineMode: String
        get() = "OnlineMode"

    override val stringOnlineModeDescription: String
        get() = "OnlineModeDescription"

    //endregion strings

    //region images

    override val imageWeb: Int
        get() = 10

    //endregion strings


}