package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> array;

    public IterableWithPolicyImpl(final T[] array) {
        this.array = List.of(array);
    }

    @Override
    public Iterator<T> iterator() {
        return this.new InnerIterator(this.array);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }

    private class InnerIterator implements Iterator<T> {

        private Iterator<T> iterator;
        private final List<T> array;

        public InnerIterator(final List<T> array) {
            this.array = array;
            this.iterator = this.array.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            return this.iterator.next();
        }
    }
}
