import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void forEach(List<T> arr, Consumer<T> elemProcessor) {
        for (T elem : arr) {
            elemProcessor.accept(elem);
        }
    }
    public static <T> List<T> filter(List<T> arr, Predicate<T> elemPredicat) {
        List<T> filtered = new ArrayList<>();
        for (T elem: arr) {
            if(elemPredicat.test(elem)) {
                filtered.add(elem);
            }
        }
        return filtered;
    }
    public static <T,R> R reduce(List<T> list, R initValue, BiFunction<T,R,R> f) {
        for (T elem: list) {
            initValue = f.apply(elem, initValue);
        }
        return initValue;

    }

    public static <T> boolean anyMatch(List<T> list, Predicate<T> predicate) {
        for (T items : list) {
            if (predicate.test(items)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean allMatch(List<T> list, Predicate<T> predicate) {
        for (T items : list) {
            if (!predicate.test(items)) {
                return false;
            }
        }
        return true;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> filtered = new ArrayList<>();
        for (T elem: list) {
            filtered.add(f.apply(elem));
        }
        return filtered;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1,2,3,4,5, 5, 5, 5 ,6,7,8,9);
        List<Integer> even = filter(arr, (elemPredicat) -> elemPredicat%2 == 0);

        forEach(even, System.out::println);
        Integer sum = reduce(arr, 0, (curr, prev) -> prev+curr);
        System.out.println("sum :" + sum);

        boolean ifAnyMatch = anyMatch(arr, (item) -> item==5);
        System.out.println("ifAnyMatch :  " + ifAnyMatch);

        boolean allMatched = allMatch(arr, (item) -> item==5);
        System.out.println("allMatched :  " + allMatched);

        List<String> reMapped = map(arr, Object::toString);
        System.out.println(reMapped);
    }
}
