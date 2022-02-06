package logging;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Samples {
    @JsonProperty("Samples")
    private List<Sample> samples = new ArrayList<>();

    public void add(Sample sample) {
        samples.add(sample);
    }
}
