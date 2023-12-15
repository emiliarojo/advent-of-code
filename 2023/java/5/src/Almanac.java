import java.util.ArrayList;
import java.util.List;

class Almanac {
    private RangeMap seedToSoil = new RangeMap();
    private RangeMap soilToFertilizer = new RangeMap();
    private RangeMap fertilizerToWater = new RangeMap();
    private RangeMap waterToLight = new RangeMap();
    private RangeMap lightToTemperature = new RangeMap();
    private RangeMap temperatureToHumidity = new RangeMap();
    private RangeMap humidityToLocation = new RangeMap();

    public void addMapping(String type, long destStart, long srcStart, long length) {
        switch (type) {
            case "seed-to-soil":
                seedToSoil.addRange(destStart, srcStart, length);
                break;
            case "soil-to-fertilizer":
                soilToFertilizer.addRange(destStart, srcStart, length);
                break;
            case "fertilizer-to-water":
                fertilizerToWater.addRange(destStart, srcStart, length);
                break;
            case "water-to-light":
                waterToLight.addRange(destStart, srcStart, length);
                break;
            case "light-to-temperature":
                lightToTemperature.addRange(destStart, srcStart, length);
                break;
            case "temperature-to-humidity":
                temperatureToHumidity.addRange(destStart, srcStart, length);
                break;
            case "humidity-to-location":
                humidityToLocation.addRange(destStart, srcStart, length);
                break;
        }
    }

    public long findLocation(long seed) {
        long soil = seedToSoil.findDestination(seed);
        long fertilizer = soilToFertilizer.findDestination(soil);
        long water = fertilizerToWater.findDestination(fertilizer);
        long light = waterToLight.findDestination(water);
        long temperature = lightToTemperature.findDestination(light);
        long humidity = temperatureToHumidity.findDestination(temperature);
        long location = humidityToLocation.findDestination(humidity);
        // System.out.println("Seed: " + seed + " -> Location: " + location);
        return location;
    }

    public List<Long> getMappingStartPoints(Range seedRange) {
        List<Long> startPoints = new ArrayList<>();
        long start = seedRange.getStart();
        long end = seedRange.getEnd();
        while (start < end) {
            long nextStart = findNextMappingStart(start, end);
            startPoints.add(start);
            start = nextStart;
        }
        return startPoints;
    }

    private long findNextMappingStart(long start, long end) {
        long nextStart = Math.min(seedToSoil.nextKey(start), soilToFertilizer.nextKey(start));
        nextStart = Math.min(nextStart, fertilizerToWater.nextKey(start));
        nextStart = Math.min(nextStart, waterToLight.nextKey(start));
        nextStart = Math.min(nextStart, lightToTemperature.nextKey(start));
        nextStart = Math.min(nextStart, temperatureToHumidity.nextKey(start));
        nextStart = Math.min(nextStart, humidityToLocation.nextKey(start));
        return (nextStart <= end) ? nextStart : end;
    }

}
