# token-source

This project implements a slow backend to test @CacheResult behaviour when
the call to cache is returning an Uni while also being slow.

The application is supposed to run on localhost:8090 and provides two REST 
endpoints:

- /token/gettoken which will delay the returned data for 10 seconds.
  This call is assumed to be used in a reactive context, but with a delay

- /token/gettokenb which also delays the response for 10 seconds
  This call is used in a traditional blocking call.
