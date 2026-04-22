package org.example.helpers

import java.util.logging.Logger

class LoggerHelper {

    companion object {
        val logger: Logger = Logger.getLogger(LoggerHelper::class.java.name)
    }
}