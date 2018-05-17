package rbs.test

import grails.converters.JSON
import grails.converters.XML

class IndexController {

    def primeService

    def index() {
        String number = params.number.toString()
        if (!number.isNumber()) {
            return render(status: 500, contentType: "text/json", text: "Param is not a number.")
        } else if (Integer.valueOf(number) <= 0) {
            return render(status: 500, contentType: "text/json", text: "Number is too small.")
        }
        String primes
        if(params.efficient){
            primes = primeService.calculatePrimesEfficiently(Integer.valueOf(number)).toString()
        }else{
            primes = primeService.calculatePrimes(Integer.valueOf(number)).toString()
        }
        Map<String, String> result = ["initial": number.toString(),
                                      "primes" : primes]
        if(request.getContentType() == "text/xml"){
            return render(contentType: "text/xml", text: result as XML)
        }else{
            return render(contentType: "text/json", text: result as JSON)
        }
    }
}
