package com.example.server.local.models.dto

data class ResponseDto(
        val header: HeaderDto,
        var body: String?,
        var filePath: String = ""
)