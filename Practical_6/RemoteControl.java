interface Switchable {
    void on();
    void off();
    boolean isOn();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

class Fan implements Switchable {
    private boolean on = false;

    public void on() {
        on = true;
        System.out.println("Fan is ON");
    }

    public void off() {
        on = false;
        System.out.println("Fan is OFF");
    }

    public boolean isOn() {
        return on;
    }
}

class Light implements Switchable {
    private boolean on = false;

    public void on() {
        on = true;
        System.out.println("Light is ON");
    }

    public void off() {
        on = false;
        System.out.println("Light is OFF");
    }

    public boolean isOn() {
        return on;
    }
}

@FunctionalInterface
interface DeviceRule {
    boolean canSwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = { new Fan(), new Light() };

        System.out.println("Toggling devices:");
        for (Switchable device : devices) {
            device.toggle();
            device.toggle();
        }

        DeviceRule anonymousRule = new DeviceRule() {
            @Override
            public boolean canSwitchOn(Switchable device, int hour) {
                return hour >= 9 && hour <= 18;
            }
        };

        DeviceRule lambdaRule = (device, hour) -> hour >= 8 && hour <= 20;

        System.out.println("Anonymous rule: " + anonymousRule.canSwitchOn(devices[0], 10));
        System.out.println("Lambda rule: " + lambdaRule.canSwitchOn(devices[1], 21));
    }
}
