package com.example.finalprojectdanielasamuil;

import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.model.dtos.FitnessClassDto;
import com.example.finalprojectdanielasamuil.model.dtos.UserDto;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls, Object... parameters) {
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;

        if (cls.equals(User.class)) {
            supplier = TestCreationFactory::newUser;
        } else if (cls.equals(UserDto.class)) {
            supplier = TestCreationFactory::newUserDto;
        } else if (cls.equals(FitnessClass.class)) {
            supplier = TestCreationFactory::newFitnessClass;
        } else if (cls.equals(FitnessClassDto.class)) {
            supplier = TestCreationFactory::newFitnessClassDto;
        } else if (cls.equals(ClassSubscription.class)) {
            supplier = TestCreationFactory::newClassSubscription;
        } else if (cls.equals(ClassSubscriptionDto.class)) {
            supplier = TestCreationFactory::newClassSubscriptionDto;
        } else {
            supplier = () -> new String("You failed.");
        }

        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                (T) finalSupplier.get()
        ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    public static Date timestamp() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
    }


    private static User newUser() {
        return User.builder()
                .id(randomInt())
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .isLoyal(false)
                .nrOfSubscriptionsSoFar(0)
                .build();
    }

    private static UserDto newUserDto() {
        return UserDto.builder()
                .id(randomInt())
                .name(randomString())
                .email(randomEmail())
                .password(randomString())
                .loyalty(false)
                .build();
    }

    private static FitnessClass newFitnessClass() {
        return FitnessClass.builder()
                .id(randomInt())
                .name(randomString())
                .price(randomInt())
                .trainer(newUser())
                .build();
    }

    private static FitnessClassDto newFitnessClassDto() {
        return FitnessClassDto.builder()
                .id(randomInt())
                .name(randomString())
                .price(randomInt())
                .trainerId(randomInt())
                .build();
    }

    private static ClassSubscription newClassSubscription() {
        return ClassSubscription.builder()
                .id(randomInt())
                .fitnessClass(newFitnessClass())
                .customer(newUser())
                .build();
    }

    private static ClassSubscriptionDto newClassSubscriptionDto() {
        return ClassSubscriptionDto.builder()
                .id(randomInt())
                .fitnessClassId(randomInt())
                .customerId(randomInt())
                .build();
    }


    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }

    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomInt())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static int randomInt() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
