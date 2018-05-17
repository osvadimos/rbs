package rbs.test

import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PrimeService)
class PrimeServiceSpec extends Specification {

    @Shared
    def primeService

    def setup() {
    }

    def cleanup() {
    }

    void "test primes for random int"() {
        List<Integer> primesFor100 = primeService.calculatePrimes(new Random().nextInt(100))
        List<Integer> testedPrimes = primesFor100.collect {
            if (isPrime(it)) it
        }
        expect: "testing after check"
        primesFor100.size() == testedPrimes.size()
    }

    static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false
        }
        return true
    }
}
