# Android Local Server SDK

### Objectives
The goal of this SDK is to allow developers to use a local Android server within their Anmobile application. In the end, this will dramatically improve the development process of integration with APIs.  

### How it works: 
  - The developers incorporate this SDK
  /app/build.gradle
```groovy
   implementation project(':localserver')
```
  - Then they initialise it with specific parameters 
   ```kotlin 
private val server = LocalServer(port = 1234)

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        server.apply {
            responses = AssetsHelper.getResponses(assets)
            start()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        server.stop()
    }
```
  - As a result, a local Android server will be made available in their mobile application. 
  
### The main features:
- Responses will be automatically detected in the assets folder]
- Text representation of a query automatically parsed and made accessible in the mobile browser.

### Todos

 - Move the server to the service in the example
