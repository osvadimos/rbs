package rbs.test

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(IndexController)
class IndexControllerSpec extends Specification {

    void "test index controller too small number"() {
        when:
        params.number = '0'
        controller.index()

        then:
        response.status == 500
        response.text == "Number is too small."
    }

    void "test index controller not a number"() {
        when:
        params.number = 'p'
        controller.index()

        then:
        response.status == 500
        response.text == "Param is not a number."
    }

    void "test index calculate primes"() {
        when:
        params.number = '10'
        controller.primeService = new PrimeService()
        controller.index()

        then:
        response.status == 200
        response.text == "{\"initial\":\"10\",\"primes\":\"[2, 3, 5, 7]\"}"
    }

    void "test index calculate primes in xml"() {
        when:
        params.number = '10'
        controller.primeService = new PrimeService()
        request.contentType = "text/xml"
        controller.index()

        then:
        response.status == 200
        response.text == "<?xml version=\"1.0\" encoding=\"UTF-8\"?><map><entry key=\"initial\">10</entry><entry key=\"primes\">[2, 3, 5, 7]</entry></map>"
    }
}
