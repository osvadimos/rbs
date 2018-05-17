To run app
  - install sdkman on your system
  - install grails 3.1.4
  ```sh
  sdk i grails 3.1.4
  ```
  - go to app dir
  - to run app (app will be available at http://localhost:8080/primes/)
  ```sh
    grails run-app
    
  ```
  
  - to test app
  ```sh
      grails test-app
      
  ```
  - to run efficiently add efficient=true in get request as
  ```
  http://localhost:8080/primes/100?efficient=true

  ```