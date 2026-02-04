package com.fib.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.math.BigInteger;

@Path("/")
public class FibResource {
    @GET
    @Path("/recursive/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    public static String fib_recursive_service(@PathParam("n") String n) {
        try {
            int num = Integer.parseInt(n);
            if (num > 0 && num < 40)
                return fib_recursive(new BigInteger(n)).toString();
        } catch (Exception e) {
        }
        throw new IllegalArgumentException();
    }

    public static BigInteger fib_recursive(BigInteger n) {
        BigInteger num = n.abs();

        if (num.compareTo(BigInteger.ONE) <= 0)
            return num;
        else
            return fib_recursive(num.subtract(BigInteger.ONE))
                    .add(fib_recursive(num.subtract(new BigInteger("2"))));
    }


    @GET
    @Path("/iterative/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    public static String fib_iterative_service(@PathParam("n") String n) {
        try {
            long num = Long.parseLong(n);
            if (num > 0 && num < 1000000)
                return fib_iterative(new BigInteger(n)).toString();
        } catch (Exception e) {
        }
        throw new IllegalArgumentException();
    }

    public static BigInteger fib_iterative(BigInteger n) {
        BigInteger num = n.abs();
        BigInteger A = BigInteger.ZERO, B = BigInteger.ONE;

        for (BigInteger i = BigInteger.ZERO; i.compareTo(num) < 0; i = i.add(BigInteger.ONE)) {
            BigInteger saveA = A;
            A = B;
            B = saveA.add(B);
        }
        return A;
    }


    @GET
    @Path("/matrix/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    public static String fib_matrix_service(@PathParam("n") String n) {
        try {
            long num = Long.parseLong(n);
            if (num > 0 && num < 1000000)
                return fib_matrix(new BigInteger(n)).toString();
        } catch (Exception e) {
        }
        throw new IllegalArgumentException();
    }

    public static BigInteger fib_matrix (BigInteger n) {
        BigInteger num = n.abs();
        BigInteger TWO = new BigInteger("2");

        if (num.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ZERO;

        else if (num.compareTo(TWO) <= 0)
            return BigInteger.ONE;

        BigInteger[][] number = {
                { BigInteger.ONE, BigInteger.ONE },
                { BigInteger.ONE, BigInteger.ZERO } };
        BigInteger[][] result = {
                { BigInteger.ONE, BigInteger.ONE },
                { BigInteger.ONE, BigInteger.ZERO } };

        while (num.compareTo(BigInteger.ZERO) > 0) {
            if (num.mod(TWO).compareTo(BigInteger.ONE) == 0)
                result = MultiplyMatrix(result, number);
            number = MultiplyMatrix(number, number);
            num = num.divide(TWO);
        }
        return result[1][1];
    }

    public static BigInteger[][] MultiplyMatrix(BigInteger[][] mat1, BigInteger[][] mat2) {
        return new BigInteger[][] {
                {
                        mat1[0][0].multiply(mat2[0][0]).add(mat1[0][1].multiply(mat2[1][0])),
                        mat1[0][0].multiply(mat2[0][1]).add(mat1[0][1].multiply(mat2[1][1]))
                },
                {
                        mat1[1][0].multiply(mat2[0][0]).add(mat1[1][1].multiply(mat2[1][0])),
                        mat1[1][0].multiply(mat2[0][1]).add(mat1[1][1].multiply(mat2[1][1]))
                }
        };
    }

    /**
     * Tests
     */
    public static void main (String [] args) {
        System.out.println(fib_iterative_service("10"));
        System.out.println(fib_recursive_service("10"));
        System.out.println(fib_matrix_service("10"));
    }
}
