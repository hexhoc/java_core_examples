package reflection;

import reflection.annotations.SimpleAnnotation;


public class DemoClass {
    public int publicField;
    private String value = "initValue";

    public DemoClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @SimpleAnnotation(Name = "Test")
    public void setValue(String value) {
        this.value = value;
    }

    private void privateMethod() {
        System.out.println(value);
        System.out.println("privateMethod executed");
    }
}
