# Ch16 Creating Restful API

- Creating controllers that serve REST resources in JSON
- Transferring control messages
- Consuming REST resources

## 16.1 The fundamentals of REST

REST is not the web services with URLs or another remote procedure call (RPC) mechanism.  
On the contrary, REST has little to do with RPC. Whereas RPC is service oriented and focused on actions and verbs, REST is resource oriented, emphasizing the things and nouns that comprise an application.  
REST is about transferring the state of resources in appropriate form from a server to a client (or vice versa).

## 16.2 Creating first REST endpoint + Working with message converter

Assuming that jackson2-databind has been added into classpath

```java
@RequestMapping(value = "/spittles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody List<Spittle> spittles(
        @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
        @RequestParam(value = "count", defaultValue = "20") int count
) {
    return spittleService.findSpittles(max, count);
}
```

## 16.3 Serving more that resources

### 16.3.1 Communicating status and errors to the client

```java
@RequestMapping(value = "/spittle/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public Spittle findSpittleById(@PathVariable(value = "id") long id) {
    Spittle spittle = spittleService.findSpittleById(id);
    if (spittle == null) {
        throw new SpittleNotFoundException(id);
    }
    return spittle;
}

@ExceptionHandler(SpittleNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public Error spittleNotFound (SpittleNotFoundException exception) {
    long id = exception.getId();
    Error error = new Error(4, "Spittle no." + id + " Not Found");
    return error;
}
```

### 16.3.2 Setting headers in the response

when setting headers, we must use ResponseHttp<?> to return

```java
@RequestMapping(value = "/spittle/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Spittle> saveSpittle (@RequestBody Spittle spittle, UriComponentsBuilder uriBuilder) {
    Spittle savedSpittle = spittleService.saveSpittle(spittle);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    URI locationUri = uriBuilder.path("/spittle/{id}").buildAndExpand(savedSpittle.getId()).toUri();
    headers.setLocation(locationUri);

    ResponseEntity<Spittle> response = new ResponseEntity<>(savedSpittle, headers, HttpStatus.CREATED);
    return response;
}
```