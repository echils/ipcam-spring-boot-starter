package com.github.ipcam;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 * <p>
 * INetworkConsumer
 *
 * @author echils
 * @since 2020-03-26 16:39
 */
@FunctionalInterface
public interface ICameraConsumer<T> {


    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);


    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

}
