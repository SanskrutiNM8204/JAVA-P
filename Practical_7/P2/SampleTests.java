package Practical_7.P2;

class SampleTests {
    @Run
    void firstTest() {
        System.out.println("firstTest ran");
    }

    @Run
    void secondTest() {
        System.out.println("secondTest ran");
    }

    void helperMethod() {
        System.out.println("helperMethod should not run");
    }
}
