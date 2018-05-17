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
        response.text == "{\"response\":{\"ERROR\":{\"code\":500,\"msg\":\"Number is too small.\"}}}"
    }

    void "test index controller not a number"() {
        when:
        params.number = 'p'
        controller.index()

        then:
        response.status == 500
        response.text == "{\"response\":{\"ERROR\":{\"code\":500,\"msg\":\"Param is not a number.\"}}}"
    }

    void "test index calculate primes"() {
        when:
        params.number = '10'
        controller.index()

        then:
        response.status == 200
        response.text == "{\"response\":{\"SUCCESS\":{\"code\":200,\"initial\":\"10\",\"primes\":[2,3,5,7]}}}"
    }
}
