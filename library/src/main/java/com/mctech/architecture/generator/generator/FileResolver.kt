package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.settings.GlobalSettings
import java.io.File
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
fun writeFile(filePath : FilePath, writerFileBlock : (out : PrintWriter) -> Unit){
    // Create file
    val file = File(filePath.getPath())

    // Check the strategy of duplicated files
    if(shouldCancelOperation(file))
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

fun createFileFolder(file: File) {
    var currentFile :File? = file
    do{
        val folder = currentFile?.parentFile

        currentFile = if(folder?.exists() == true){
            null
        }else{
            folder?.mkdirs()
            folder?.parentFile
        }
    }while (currentFile != null)
}

private fun shouldCancelOperation(file: File): Boolean
        = file.exists() && GlobalSettings.fileDuplicatedStrategy.shouldCancelOperation(file)


fun PrintWriter.blankLine() = println("")
fun PrintWriter.printPackage(value : String) = println(value)
fun PrintWriter.printImport(value : String) = println(value)
fun PrintWriter.printTabulate(value : String, countTabulate : Int = 1) = println(getTabulateString(countTabulate) + value)

private fun getTabulateString(count : Int) : String{
    var tabulate = ""
    for (i in 0 until count){
        tabulate += "\t"
    }
    return tabulate
}