package com.kevinschildhorn.fotopresenter.data.network

interface NetworkDirectory {
    val name: String
    val fileExtension: String?
    val id:Int
}

class MockNetworkDirectory(
    override val name: String,
    override val fileExtension: String? = null,
    override val id: Int,
) : NetworkDirectory