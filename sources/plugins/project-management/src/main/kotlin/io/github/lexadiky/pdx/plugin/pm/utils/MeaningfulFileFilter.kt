package io.github.lexadiky.pdx.plugin.pm.utils

import java.io.File
import java.io.FileFilter

object MeaningfulFileFilter : FileFilter {

    override fun accept(file: File): Boolean {
        val fileNotHidden = !file.isHidden
        val nonEmptyDirectory = file.isDirectory && file.list()?.isEmpty() == true
        return fileNotHidden || nonEmptyDirectory
    }
}
