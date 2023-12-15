
import java.util.NavigableMap;
import java.util.TreeMap;

class RangeMap {
    private final NavigableMap<Long, Long> rangeMappings;

    public RangeMap() {
        rangeMappings = new TreeMap<>();
    }

    public void addRange(long destStart, long srcStart, long length) {
        long srcEnd = srcStart + length - 1;
        rangeMappings.subMap(srcStart, true, srcEnd, true).clear();
        rangeMappings.put(srcStart, destStart);
    }

    public long findDestination(long srcNumber) {
        Long srcStart = rangeMappings.floorKey(srcNumber);

        if (srcStart != null) {
            long destStart = rangeMappings.get(srcStart);
            return destStart + (srcNumber - srcStart);
        } else {
            return srcNumber;
        }
    }

    public long nextKey(long key) {
        Long higherKey = rangeMappings.higherKey(key);
        return (higherKey != null) ? higherKey : Long.MAX_VALUE;
    }

}
