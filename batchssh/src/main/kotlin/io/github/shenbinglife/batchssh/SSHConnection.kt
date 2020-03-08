package io.github.shenbinglife.batchssh

import com.jcraft.jsch.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.io.File
import java.io.InputStream
import java.lang.StringBuilder
import java.util.*


class SSHConnection(
        private val host: String,
        private val username: String,
        port: Int,
        pwd: String,
        idRsaPath: String,
        timeout: Long
) :
        Closeable {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(SSHConnection::class.java)

    }


    var sshSession: Session
    var sshChannel: Channel? = null

    init {
        LOGGER.info("$username try to login $host using jsch.")
        val jsch = JSch()
        jsch.getSession(username, host, port)
        if (idRsaPath.isNotBlank()) {
            jsch.addIdentity(idRsaPath)
        }
        sshSession = jsch.getSession(username, host, port)
        if (idRsaPath.isNotBlank()) {
            sshSession.setConfig("PreferredAuthentications", "publickey")
        }
        if (pwd.isNotBlank()) {
            sshSession.setPassword(pwd)
        }
        val sshConfig = Properties()
        sshConfig.put("StrictHostKeyChecking", "no")
        sshSession.setConfig(sshConfig)
        val tout = timeout.toInt()
        if (tout < 0) {
            throw IllegalArgumentException("ssh time out can not  > ${Int.MAX_VALUE} or < 0")
        }
        sshSession.timeout = tout
        sshSession.connect()
        LOGGER.info("$username login $host using jsch at ${System.currentTimeMillis()}")
    }

    override fun close(): Unit {
        if (sshChannel != null && sshChannel!!.isConnected) {
            sshChannel!!.disconnect()
        }
        if (sshSession.isConnected) {
            sshSession.disconnect()
        }
    }

    fun scp(scpFrom: String, scpTo: String) {
        LOGGER.info("scp $scpFrom to $scpTo")
        if (sshChannel == null) {
            sshChannel = sshSession.openChannel("sftp")
            val scpChannel = sshChannel as ChannelSftp
            scpChannel.connect()
        }
        val scpChannel = sshChannel as ChannelSftp
        val sourceFile = File(scpFrom)
        if (!sourceFile.exists()) {
            LOGGER.warn("scp source file not exists: $scpFrom")
        }
        if (sourceFile.isDirectory) {
            sourceFile.listFiles()?.forEach {
                val mkdir = scpTo + "/" + sourceFile.name
                LOGGER.debug("scp ${it.canonicalPath} to $mkdir")
                try {
                    scpChannel.cd(mkdir)
                } catch (e: Exception) {
                    scpChannel.mkdir(mkdir)
                }
                val target = mkdir + "/" + it.name
                scp(it.canonicalPath, target)
            }
        } else {
            scpChannel.put(scpFrom, scpTo, ChannelSftp.OVERWRITE);
        }
        scpChannel.disconnect()
        this.sshChannel = null
    }

    fun exec(cmd: String): String {
        LOGGER.info("$host exec: $cmd")
        if (sshChannel == null) {
            sshChannel = sshSession.openChannel("exec")

        }
        val execChannel = sshChannel as ChannelExec
        execChannel.setCommand(cmd)
        execChannel.connect()

        val errProcessor =
                StreamProcessor(host, execChannel.errStream)
        val inProcessor =
                StreamProcessor(host, execChannel.inputStream)
        errProcessor.start()
        inProcessor.start()
        errProcessor.join()
        inProcessor.join()
        execChannel.disconnect()
        this.sshChannel = null
        return inProcessor.output()
    }

}

class StreamProcessor(private val host: String, private val stream: InputStream) : Thread() {
    private val builder: StringBuilder = StringBuilder()

    override fun run() {
        super.run()
        val reader = stream.bufferedReader(Charsets.UTF_8)
        reader.lines().forEach {
            builder.appendln(it)
            SSHConnection.LOGGER.debug("$host output: $it")
        }
    }

    fun output(): String {
        return builder.toString()
    }

}
