package logging;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Sample implements Comparable<Sample>{
    private int inputParam;
    private int measuredTime;

    public Sample(Integer param, Integer measure) {
        inputParam = param;
        measuredTime = measure;
    }

    public int getInputParam() {
        return inputParam;
    }

    public int getMeasuredTime() {
        // don't remove. it's used by jackson when converting Samples list to json
        return measuredTime;
    }

    @Override
    public int compareTo(Sample otherSample) {
        return Integer.compare(inputParam, otherSample.getInputParam());
    }
}
