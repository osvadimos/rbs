package rbs.test

import java.util.concurrent.ConcurrentHashMap

class PrimeService {

    private ConcurrentHashMap<Integer, List<Integer>> cacheMap = new ConcurrentHashMap<>()

    /**
     *
     * @param int number
     * @return primes till that number
     */
    List<Integer> calculatePrimes(final int number) {
        if(cacheMap[number]){
            log.debug "Found primes for number:${number} in cache"
            return cacheMap[number]
        }
        List<Integer> primes = []
        (2..number).each { int n ->
            (2..n).each { int d ->
                if(n % d == 0 && n != d)
                    primes.add(n)
            }
        }
        primes = (1..number) - primes - 1
        log.debug "Calculated primes for number:${number}"
        cacheMap.put(number, primes)
        return primes
    }
}
