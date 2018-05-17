package rbs.test

import org.h2.mvstore.ConcurrentArrayList

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

class PrimeService {

    private ConcurrentHashMap<Integer, List<Integer>> cacheMap = new ConcurrentHashMap<>()

    /**
     *
     * @param int number
     * @return primes till that number
     */
    List<Integer> calculatePrimes(final int number) {
        if (cacheMap[number]) {
            log.debug "Found primes for number:${number} in cache"
            return cacheMap[number]
        }
        List<Integer> primes = []
        (2..number).each { int n ->
            (2..n).each { int d ->
                if (n % d == 0 && n != d)
                    primes.add(n)
            }
        }
        primes = (1..number) - primes - 1
        log.debug "Calculated primes for number:${number}"
        cacheMap.put(number, primes)
        return primes
    }

    /**
     *
     * @param int number
     * @return primes till that number
     */
    List<Integer> calculatePrimesEfficiently(final int number) {
        if (cacheMap[number]) {
            log.debug "Found primes for number:${number} in cache"
            return cacheMap[number]
        }
        if (number == 1) return []
        if (number == 2) return [2]
        if (number == 3) return [2, 3]

        ConcurrentArrayList<Integer> notPrimes = []
        ExecutorService executor = Executors.newCachedThreadPool()
        (2..number).each { int n ->
            executor.execute {
                (2..n).each { int d ->
                    if (n % d == 0 && n != d)
                        notPrimes.add(n)
                }
            }

        }
        executor.shutdown()
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)
        List<Integer> primes = (1..number) - notPrimes - 1
        log.debug "Calculated primes for number:${number}"
        cacheMap.put(number, primes)
        return primes
    }
}
