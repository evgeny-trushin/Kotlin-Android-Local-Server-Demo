package com.example.server.local.models.dto

import com.google.gson.annotations.SerializedName

/**
 * en.wikipedia.org/wiki/List_of_HTTP_header_fields
 */
data class HeaderDto(
        @SerializedName("DelayMillis") val DelayMillis: String?,
        @SerializedName("uri") val uri: String,
        @SerializedName("A-IM") val AIM: String?,    //Accept	Media type(s) that is(/are) acceptable for the response. See Content negotiation.	Accept: text/html	Permanent
        @SerializedName("Status-Code") val statusCode: String?,
        @SerializedName("Accept-Charset") val AcceptCharset: String?,
        //Character sets that are acceptable.	Accept-Charset: utf-8	Permanent
        /**
        Accept-Encoding	//List of acceptable encodings. See HTTP compression.	Accept-Encoding: gzip, deflate	Permanent
        Accept-Language	//List of acceptable human languages for response. See Content negotiation.	Accept-Language: en-US	Permanent
        Accept-Datetime	//Acceptable version in time.	Accept-Datetime: Thu, 31 May 2007 20:35:00 GMT	Provisional
        Access-Control-Request-Method//,
        Access-Control-Request-Headers//[8]	Initiates a request for cross-origin resource sharing with Origin (below).	Access-Control-Request-Method: GET	Permanent: standard
        Authorization	//Authentication credentials for HTTP authentication.	Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==	Permanent
        Cache-Control	//Used to specify directives that must be obeyed by all caching mechanisms along the request-response chain.	Cache-Control: no-cache	Permanent
        Connection	//Control options for the current connection and list of hop-by-hop request fields.[9]
        //Must not be used with HTTP/2.[10]

        //Connection: keep-alive
        //Connection: Upgrade

        Permanent
        Content-Length	//The length of the request body in octets (8-bit bytes).	Content-Length: 348	Permanent
        Content-MD5	//A Base64-encoded binary MD5 sum of the content of the request body.	Content-MD5: Q2hlY2sgSW50ZWdyaXR5IQ==	Obsolete[11]
         */
        @SerializedName("Content-Type")
        val ContentType: String   //The Media type of the body of the request (used with POST and PUT requests).	Content-Type: application/x-www-form-urlencoded	Permanent
        /**Cookie	//An HTTP cookie previously sent by the server with Set-Cookie (below).	Cookie: $Version=1; Skin=new;	Permanent: standard
        Date	//The date and time that the message was originated (in "HTTP-date" format as defined by RFC 7231 Date/Time Formats).	Date: Tue, 15 Nov 1994 08:12:31 GMT	Permanent
        Expect	//Indicates that particular server behaviors are required by the client.	Expect: 100-continue	Permanent
        Forwarded	//Disclose original information of a client connecting to a web server through an HTTP proxy.[12]	Forwarded: for=192.0.2.60;proto=http;by=203.0.113.43 Forwarded: for=192.0.2.43, for=198.51.100.17	Permanent
        From	//The email address of the user making the request.	From: user@example.com	Permanent
        Host	//The domain name of the server (for virtual hosting), and the TCP port number on which the server is listening. The port number may be omitted if the port is the standard port for the service requested.
        //Mandatory since HTTP/1.1.[13] If the request is generated directly in HTTP/2, it should not be used.[14]

        //Host: en.wikipedia.org:8080
        //Host: en.wikipedia.org

        //Permanent
        If-Match	//Only perform the action if the client supplied entity matches the same entity on the server. This is mainly for methods like PUT to only update a resource if it has not been modified since the user last updated it.	If-Match: "737060cd8c284d8af7ad3082f209582d"	Permanent
        If-Modified-Since	//Allows a 304 Not Modified to be returned if content is unchanged.	If-Modified-Since: Sat, 29 Oct 1994 19:43:31 GMT	Permanent
        If-None-Match	//Allows a 304 Not Modified to be returned if content is unchanged, see HTTP ETag.	If-None-Match: "737060cd8c284d8af7ad3082f209582d"	Permanent
        If-Range	//If the entity is unchanged, send me the part(s) that I am missing; otherwise, send me the entire new entity.	If-Range: "737060cd8c284d8af7ad3082f209582d"	Permanent
        If-Unmodified-Since	//Only send the response if the entity has not been modified since a specific time.	If-Unmodified-Since: Sat, 29 Oct 1994 19:43:31 GMT	Permanent
        Max-Forwards	//Limit the number of times the message can be forwarded through proxies or gateways.	Max-Forwards: 10	Permanent
        Origin//[8]	Initiates a request for cross-origin resource sharing (asks server for Access-Control-* response fields).	Origin: http://www.example-social-network.com	Permanent: standard
        Pragma	//Implementation-specific fields that may have various effects anywhere along the request-response chain.	Pragma: no-cache	Permanent
        Proxy-Authorization//	Authorization credentials for connecting to a proxy.	Proxy-Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==	Permanent
        Range	//Request only part of an entity. Bytes are numbered from 0. See Byte serving.	Range: bytes=500-999	Permanent
        Referer //[sic]	This is the address of the previous web page from which a link to the currently requested page was followed. (The word “referrer” has been misspelled in the RFC as well as in most implementations to the point that it has become standard usage and is considered correct terminology)	Referer: http://en.wikipedia.org/wiki/Main_Page	Permanent
        TE//	The transfer encodings the user agent is willing to accept: the same values as for the response header field Transfer-Encoding can be used, plus the "trailers" value (related to the "chunked" transfer method) to notify the server it expects to receive additional fields in the trailer after the last, zero-sized, chunk.
        //Only trailers is supported in HTTP/2.[10]

        //TE: //trailers, deflate	Permanent
        User-Agent	//The user agent string of the user agent.	User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0	Permanent
        //Upgrade	Ask the server to upgrade to another protocol.
        //Must not be used in HTTP/2.[10]
         */
)