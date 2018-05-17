package rbs.test

import grails.transaction.Transactional

@Transactional
class PrimeService {

    static List<Integer> calculatePrimes(final int number) {
        List<Integer> primes = []
        (2..number).each { int n ->
            (2..n).each { int d ->
                if(n % d == 0 && n != d)
                    primes.add(n)
            }
        }
        primes = (1..number) - primes - 1
        return primes
    }
}
