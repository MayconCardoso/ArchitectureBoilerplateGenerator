package io.github.mayconcardoso.boilerplate.generator.strategy

import java.io.File

/**
 * The duplicate file strategy. It says the generator how handle when a file that is being generated already exists.
 *
 * @author MAYCON CARDOSO on 2019-11-28.
 */
sealed class FileDuplicatedStrategy {
  abstract fun shouldCancelOperation(file: File): Boolean

  /**
   * It is gonna replace the existent file.
   */
  object Replace : FileDuplicatedStrategy() {
    override fun shouldCancelOperation(file: File): Boolean = !file.delete()
  }

  /**
   * It is gonna ignore the new file and keep the existent one.
   */
  object Ignore : FileDuplicatedStrategy() {
    override fun shouldCancelOperation(file: File): Boolean = true
  }
}