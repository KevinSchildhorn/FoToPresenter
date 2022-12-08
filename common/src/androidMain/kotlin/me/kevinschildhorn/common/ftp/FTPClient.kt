package me.kevinschildhorn.common.ftp

import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.xfer.FileSystemFile


class FTPClient {
    fun test(){
        val ssh = SSHClient()
        ssh.loadKnownHosts()
        ssh.connect("host")
        try {
            ssh.authPassword("username", "password")
            val sftp = ssh.newSFTPClient()
            sftp.use { sftp ->
                sftp.put(FileSystemFile("/path/of/local/file"), "/path/of/ftp/file")
            }
        } finally {
            ssh.disconnect()
        }
    }
}