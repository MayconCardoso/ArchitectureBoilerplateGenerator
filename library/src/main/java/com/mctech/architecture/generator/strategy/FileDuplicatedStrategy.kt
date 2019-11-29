package com.mctech.architecture.generator.strategy

import java.io.File

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
sealed class FileDuplicatedStrategy {
    abstract fun shouldCancelOperation(file: File): Boolean

    object Replace : FileDuplicatedStrategy() {
        override fun shouldCancelOperation(file: File): Boolean = !file.delete()
    }

    object Ignore : FileDuplicatedStrategy() {
        override fun shouldCancelOperation(file: File): Boolean = true
    }
}