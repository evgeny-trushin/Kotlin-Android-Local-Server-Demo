package com.example.server.local.models.assets.helpers

import android.content.res.AssetManager
import android.util.Log
import com.example.server.local.models.dto.HeaderDto
import com.example.server.local.models.dto.ResponseDto
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class AssetsHelper {

    companion object {

        private const val ASSETS_FOLDER: String = "responses"
        private const val DEFAULT_RESPONSE_FOLDER: String = "default"
        private val TAG: String = this::class.java.simpleName

        @JvmStatic
        fun getResponses(assets: AssetManager): MutableMap<String, ResponseDto> {
            val assetsList = mutableListOf<String>()
            val responsesList = mutableMapOf<String, ResponseDto>()

            assets.list(DefaultFolders.DEFAULT_FOLDER.folderName).forEach { folder ->
                try {
                    addRecursivelyAssetFilesToMutableList(assets, DefaultFolders.DEFAULT_FOLDER.plus(folder), assetsList)
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                }
            }

            assetsList.forEach { fullFilePath ->
                val content: String
                try {
                    when (ResponseFileType.getType(fullFilePath)) {
                        ResponseFileType.RESPONSE_HEADER -> {
                            content = assets.open(fullFilePath).readTextAndClose()
                            val json = Gson().fromJson(content, HeaderDto::class.java)
                            val contentFileName = ResponseFileType.getBodyFilename(fullFilePath)
                            val body: String? = try {
                                assets.open(contentFileName).readTextAndClose()
                            } catch (ignore: Exception) {
                                null
                            }
                            responsesList[json.uri] = ResponseDto(
                                    header = json,
                                    body = body
                            )
                        }
                        ResponseFileType.RESPONSE_BODY -> {
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            return responsesList
        }

        @Throws(IOException::class)
        @JvmStatic
        private fun addRecursivelyAssetFilesToMutableList(manager: AssetManager,
                                                          folder: String, existingList: MutableList<String>,
                                                          includeFolders: Boolean = false, recursive: Boolean = true,
                                                          pathSeparator: String = File.separator) {
            var subFolder = folder
            val files = manager.list(subFolder)
            if (subFolder.isNotEmpty()) {
                subFolder = subFolder.plus(pathSeparator)
            }
            for (file in files) {
                val innerPath = subFolder.plus(file)
                val innerList = manager.list(innerPath)
                if (innerList != null && innerList.isNotEmpty()) {
                    if (includeFolders) {
                        existingList.add(innerPath)
                    }
                    if (recursive) {
                        addRecursivelyAssetFilesToMutableList(manager, innerPath, existingList, includeFolders, recursive, pathSeparator)
                    }
                } else {
                    existingList.add(innerPath)
                }
            }
        }

        private fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
            return this.bufferedReader(charset).use { it.readText() }
        }
    }

    enum class DefaultFolders(val folderName: String) {
        DEFAULT_FOLDER(ASSETS_FOLDER),
        DEFAULT_RESPONSE_FOLDER(ASSETS_FOLDER.plus(File.separator).plus(DEFAULT_RESPONSE_FOLDER));

        fun plus(dir: String) = this.folderName.plus(File.separator).plus(dir)
    }

    enum class ResponseFileType(val suffix: String = "") {
        RESPONSE_HEADER(".header."),
        RESPONSE_BODY(".body.");

        companion object {
            fun getType(fileName: String) = if (fileName.contains(RESPONSE_HEADER.suffix)) {
                RESPONSE_HEADER
            } else {
                RESPONSE_BODY
            }

            fun getBodyFilename(headerName: String) = headerName.replace(
                    RESPONSE_HEADER.suffix,
                    RESPONSE_BODY.suffix,
                    true
            )
        }
    }
}