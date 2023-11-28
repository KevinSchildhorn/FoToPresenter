package me.kevinschildhorn.common.network.smb

import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.share.DiskShare
import com.hierynomus.smbj.session.Session
import com.hierynomus.smbj.share.Share

class SMBClientTest {

    fun connect(serverName: String, username: String, password: String, domain: String){
        val client = SMBClient()
        client.connect(serverName).use { connection ->
            val ac =
                AuthenticationContext(
                    username,
                    password.toCharArray(),
                    domain,
                )
            val session: Session = connection.authenticate(ac)
            val share:Share = session.connectShare("SHARENAME")
            (share as? DiskShare)?.let {
                for (f in it.list("FOLDER", "*.TXT")) {
                    println("File : " + f.fileName)
                }
            }
        }
    }
}