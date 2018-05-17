package rbs.test

import grails.converters.JSON


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
        return render(contentType: "text/json", text: result as JSON)
    }
}
