package io.github.mayconcardoso.boilerplate.generator.core.generator

import io.github.mayconcardoso.boilerplate.generator.core.path.FilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.strategy.FileDuplicatedStrategy
import java.io.File
import java.io.PrintWriter

fun writeFile(
  filePath: FilePath,
  fileDuplicatedStrategy: FileDuplicatedStrategy = GlobalSettings.fileDuplicatedStrategy,
  writerFileBlock: (out: PrintWriter) -> Unit
) {
  // Create file
  val file = File(filePath.getPath())

  // Check the strategy of duplicated files
  if (shouldCancelOperation(file, fileDuplicatedStrategy))
    return

  // Log output
  println("Generating file: " + file.absolutePath)

  // Create folder
  createFileFolder(file)

  // Write content
  file.createNewFile()
  file.printWriter().use { out ->
    writerFileBlock.invoke(out)
  }
}

fun writeFileString(
  filePath: FilePath,
  fileDuplicatedStrategy: FileDuplicatedStrategy = GlobalSettings.fileDuplicatedStrategy,
  writerFileBlock: () -> String
) = writeFile(filePath, fileDuplicatedStrategy) {
  it.println(writerFileBlock())
}

fun readFile(filePath: FilePath): List<String> {
  // Create file
  val file = File(filePath.getPath())

  // Check the strategy of duplicated files
  if (file.exists().not())
    return listOf()

  // Read file
  return file.readLines()
}

fun copyFile(
  file: File,
  target: File,
  fileDuplicatedStrategy: FileDuplicatedStrategy = GlobalSettings.fileDuplicatedStrategy,
) {
  // Check the strategy of duplicated files
  if (shouldCancelOperation(target, fileDuplicatedStrategy)) {
    return
  }

  // Copy file
  file.copyTo(target, overwrite = true)
}

private fun createFileFolder(file: File) {
  var currentFile: File? = file
  do {
    val folder = currentFile?.parentFile

    currentFile = if (folder?.exists() == true) {
      null
    } else {
      folder?.mkdirs()
      folder?.parentFile
    }
  } while (currentFile != null)
}

private fun shouldCancelOperation(
  file: File,
  fileDuplicatedStrategy: FileDuplicatedStrategy
): Boolean {
  return file.exists() && fileDuplicatedStrategy.shouldCancelOperation(file)
}

private fun getTabulateString(count: Int): String {
  var tabulate = ""
  for (i in 0 until count) {
    tabulate += "  "
  }
  return tabulate
}

fun PrintWriter.blankLine() = println("")
fun PrintWriter.printPackage(value: String) = println(value)
fun PrintWriter.printImport(value: String) = println(value)
fun PrintWriter.printTabulate(value: String, countTabulate: Int = 1) =
  println(getTabulateString(countTabulate) + value)

fun PrintWriter.printDoubleTabulate(value: String) = printTabulate(value, 2)
fun PrintWriter.printTripleTabulate(value: String) = printTabulate(value, 3)