package me.kevinschildhorn.common.layers.domain

/**
Saves sign in credentials to the repository
 **/
class ConnectToSFTPUseCase() {

    operator fun invoke(address: String, username: String, password: String): Boolean =
        try {
            //val client = SFTPClient()
            //client.connect(address, username, password)

            true
        } catch (e: Exception) {
            false
        }
}
