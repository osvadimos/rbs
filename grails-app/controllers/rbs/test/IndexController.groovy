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
        Map<String, String> result = ["initial": number.toString(),
                                      "primes" : primeService.calculatePrimes(Integer.valueOf(number)).toString()]
        if(request.getContentType().toString() == "text/xml"){
            return render(contentType: "text/xml", text: result as XML)
        }else{
            return render(contentType: "text/json", text: result as JSON)
        }
    }
}
