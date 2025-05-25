package ppss.ejercicio1;

public class Sample {

    public int countValues(int [] data) {
        int age; int index = 0;
        final int min_value = 10;
        final int max_value = 80;
        boolean stop = false;
        int result = 0;

        if (data.length >0) {
            while (index < data.length && !stop) {
                if (data[index] >= min_value && data[index] <= max_value) {
                    result = result + data[index];
                } else {
                    stop = true;
                    result = 0;
                }
                index++;
            }
        }
        return result;
    }

    public boolean isValid(int [] data) {
        int age; int index = 0;
        final int min_value = 10;
        final int max_value = 80;
        boolean error = false;

        if (data.length >0) {  
            while (index < data.length && !error) {
                if (data[index] >= min_value && data[index] <= max_value) {
                    index++;
                } else {
                    error = true;
                }
            }
        } else error = true;
        return !error;
    }

    public Integer maxValue(int [] data) {
        Integer max= null;
        if (data == null) {
            return null;
        }
        if (isValid(data)) {
            max= 0;
            for(int i=0; i<data.length; i++) {
                if (data[i] > max) {
                    max= data[i];
                }
            }
        }
        return max;
    }
}
