package rbs.test



class IndexController {

    def primeService

    def index() {
        String number = params.number.toString()
        if (!number.isNumber()) {
            return render(status: 500,  contentType: "text/json") { response ERROR: [code: 500, msg: "Param is not a number."]}
        } else if (Integer.valueOf(number) <= 0) {
            return render(status: 500, contentType: "text/json") { response ERROR: [code: 500, msg: "Number is too small."] }
        }
        return render(contentType: "text/json") {
            response SUCCESS: [code    : 200,
                               initial : number,
                               primes: primeService.calculatePrimes(Integer.valueOf(number))]
        }
    }
}
