package com.example.server.local.models

import com.example.server.local.models.dto.ResponseDto
import fi.iki.elonen.NanoHTTPD

class LocalServer(hostname: String? = null, port: Int = defaultPort) :
        NanoHTTPD(hostname, port) {

    lateinit var responses: MutableMap<String, ResponseDto>

    companion object {

        const val ERROR_DESCRIPTION = "#ERROR_DESCRIPTION#"
        const val URI_EXAMPLE = "#URI_EXAMPLE#"
        const val DEFAULT_CONTENT_TYPE = "text/html"
        const val DEFAULT_CONTENT = "<head/><body>" +
                "<big>It works! " +
                "\n<br/> <b>" + ERROR_DESCRIPTION + "</b>" +
                "\n<br/> However, there is an issue with your assets. " +
                "\n<br/> TODO Create " +
                " \n<br/> <b>src/main/assets/response/test.header.json</b>" +
                " \n<br/> Content: {\"uri\": \"" + URI_EXAMPLE + "\"}" +
                " \n<br/> and" +
                " \n<br/> <b>src/main/assets/response/test.body.*</b> " +
                "</big></body>"

        const val defaultPort = 7777
    }

    override fun serve(session: IHTTPSession?): Response {
        var code: Response.Status? = null
        var content: String? = null
        var contentType: String? = null
        if (responses.containsKey(session?.uri)) {
            responses[session?.uri]?.let { response ->
                response.header.statusCode?.let { localCode ->
                    val intCode = localCode.toInt()
                    Response.Status.values().asSequence().filter { nanoCode ->
                        nanoCode.requestStatus == intCode
                    }.forEach { nanoCode ->
                        code = nanoCode
                    }
                }
                code = code ?: Response.Status.OK
                content = response.body ?: code!!.description
                contentType = response.header.ContentType
            }
        }
        code = code ?: Response.Status.NOT_FOUND
        content = content
                ?:
                DEFAULT_CONTENT.
                    replace(URI_EXAMPLE,session?.uri.toString()).
                    replace(ERROR_DESCRIPTION, code!!.description)
        contentType = contentType ?: DEFAULT_CONTENT_TYPE

        return newFixedLengthResponse(code, contentType, content)
    }
}