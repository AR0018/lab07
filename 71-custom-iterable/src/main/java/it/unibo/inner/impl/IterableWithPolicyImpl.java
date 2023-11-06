package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> array;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] array) {
        this(array, new Predicate<T>(){
            @Override
            public boolean test(T t) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate) {
        this.array = List.of(array);
        this.predicate = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return this.new InnerIterator(this.array);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    private class InnerIterator implements Iterator<T> {

        private Iterator<T> iterator;
        private final List<T> filteredArray;

        public InnerIterator(final List<T> array) {
            /*
             * Creates a filtered copy of the array with just the elements
             * for which predicate.test() returns true.
             * The variable "iterator" is initialized to the iterator of this copy
             */
            this.filteredArray = new ArrayList<>();
            for(final T elem : array) {
                if(predicate.test(elem)) {
                    this.filteredArray.add(elem);
                }
            }
            this.iterator = this.filteredArray.iterator();
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
