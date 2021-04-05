package com.turkcell.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocApplication.class, args);
    }

    /*
                List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            intList.add(i);
        }
        long startTime = new Date().getTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        final List<Integer> primes = forkJoinPool.submit(()-> 
                intList.parallelStream().parallel()
                        .filter(item -> item < 999999)
                        .collect(Collectors.toList()) ).get();
        System.out.println((new Date().getTime()-startTime));
     */
}
